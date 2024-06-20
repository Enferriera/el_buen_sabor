package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.*;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.Rol;
import com.example.buensaborback.domain.enums.TipoEnvio;
import com.example.buensaborback.repositories.FacturaRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import com.itextpdf.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PedidoServiceImpl extends BaseServiceImp<Pedido, Long> implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ArticuloInsumoService articuloInsumoService;
    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FacturaRepository facturaRepository;

//    @Override
//    public void validarStock(Set<DetallePedido> detalles) throws RuntimeException {
//        for (DetallePedido detalle : detalles) {
//            Articulo articulo = detalle.getArticulo();
//            if (articulo instanceof ArticuloInsumo) {
//                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
//                if (!insumo.tieneStockSuficiente(detalle.getCantidad())) {
//                    throw new RuntimeException("Stock insuficiente para el artículo: " + insumo.getDenominacion());
//                }
//                // Decrementar el stock
//                insumo.setStockActual(insumo.getStockActual() - detalle.getCantidad());
//                articuloService.update(insumo, insumo.getId());
//            } else{
//                ArticuloManufacturado articuloManufacturado = articuloManufacturadoService.getById(articulo.getId());
//                for (ArticuloManufacturadoDetalle amd : articuloManufacturado.getArticuloManufacturadoDetalles()){
//                    if (!amd.getArticuloInsumo().tieneStockSuficiente(detalle.getCantidad())) {
//                        throw new RuntimeException("Stock insuficiente para el artículo: " + amd.getArticuloInsumo().getDenominacion());
//                    }
//                    // Decrementar el stock
//                    amd.getArticuloInsumo().setStockActual(amd.getArticuloInsumo().getStockActual() - detalle.getCantidad());
//                    articuloService.update(amd.getArticuloInsumo(), amd.getArticuloInsumo().getId());
//                }
//            }
//        }
//    }

    @Override
    public boolean aplicarDescuento(Pedido pedido) {
        if (pedido.getTipoEnvio() == TipoEnvio.TAKE_AWAY) {
            pedido.setTotal(pedido.getTotal() * 0.9); // Aplicar 10% de descuento
            return true;
        }
        return false;
    }


    @Override
    public void calcularTiempoEstimado(Pedido pedido) {
        int tiempoArticulos = pedido.getDetallePedidos().stream()
                .mapToInt(detalle -> {
                    if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) detalle.getArticulo();
                        return articuloManufacturado.getTiempoEstimadoMinutos();
                    } else {
                        return 0;
                    }
                })
                .sum();
        int tiempoCocina = obtenerPedidosEnCocina().stream()
                .flatMap(p -> p.getDetallePedidos().stream())
                .mapToInt(detalle -> {
                    if (detalle.getArticulo() instanceof ArticuloManufacturado) {
                        ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) detalle.getArticulo();
                        return articuloManufacturado.getTiempoEstimadoMinutos();
                    } else {
                        return 0;
                    }
                })
                .sum();

        int cantidadCocineros = contarCocineros();
        //Si no hay cocineros disponibles, devuelve 0
        int tiempoCocinaPromedio = cantidadCocineros > 0 ? tiempoCocina / cantidadCocineros : 0;

        int tiempoDelivery = pedido.getTipoEnvio() == TipoEnvio.DELIVERY ? 10 : 0;
        pedido.setHoraEstimadaFinalizacion(LocalTime.now().plusMinutes(tiempoArticulos + tiempoCocinaPromedio + tiempoDelivery));
    }

    @Override
    public List<Pedido> obtenerPedidosEnCocina() {
        // Implementar la lógica para obtener los pedidos que están en preparación
        return pedidoRepository.findByEstadoPedido(EstadoPedido.PREPARACION);
    }

    @Override
    public int contarCocineros() {
        return empleadoService.contarPorRol(Rol.COCINERO);
    }

    @Override
    public Pedido cambiaEstado(EstadoPedido estado, Long id) {
        Pedido pedido = getById(id);
        pedido.setEstadoPedido(estado);

        if (estado == EstadoPedido.PREPARACION) {
            Factura factura = new Factura();
            factura.setFechaFacturacion(LocalDate.now());
            if (aplicarDescuento(pedido)){
                factura.setMontoDescuento(10);
            }else {
                factura.setMontoDescuento(0);
            }
            factura.setFormaPago(pedido.getFormaPago());
            factura.setTotalVenta(pedido.getTotal());
            pedido.setFactura(factura);

            facturaRepository.save(factura);
        }


        if (estado == EstadoPedido.COMPLETADO) {
            try {
                // creamos la factura  la factura PDF
                byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                // traemos el email del cliente
                String emailCliente = pedido.getCliente().getEmail();

                // Enviar el email con la factura
                emailService.sendMail(facturaPdf, emailCliente, null, "Factura de Pedido " + pedido.getId(), "Adjunto encontrará la factura de su pedido.", "factura_" + pedido.getId() + ".pdf");

            } catch (IOException | java.io.IOException e) {
                throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
            }

        }
        if (estado == EstadoPedido.CANCELADO) {
           // revertirStock(pedido.getDetallePedidos());
        }


        return pedidoRepository.save(pedido);
    }

   /* private void revertirStock(Set<DetallePedido> detalles) {
        for (DetallePedido detalle : detalles) {
            Articulo articulo = detalle.getArticulo();
            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                insumo.setStockActual(insumo.getStockActual() + detalle.getCantidad());
                articuloInsumoService.update(insumo, insumo.getId());
            } else {
                ArticuloManufacturado articuloManufacturado = articuloManufacturadoService.getById(articulo.getId());
                for (ArticuloManufacturadoDetalle amd : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo insumo = amd.getArticuloInsumo();
                    insumo.setStockActual(insumo.getStockActual() + detalle.getCantidad());
                    articuloInsumoService.update(insumo, insumo.getId());
                }
            }
        }
    }
    private void calcularTotal(Pedido pedido) {
        double total = 0.0;
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            total += detalle.getCantidad() * detalle.getArticulo().getPrecioVenta();
        }
        pedido.setTotal(total);
    }
    private void calcularTotalCosto(Pedido pedido) {
        double totalCosto = 0.0;
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Articulo articulo = detalle.getArticulo();
            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                totalCosto += detalle.getCantidad() * insumo.getPrecioCompra();
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                    totalCosto += detalleManufacturado.getCantidad() * insumo.getPrecioCompra();
                }
            }
        }
        pedido.setTotalCosto(totalCosto);

    }*/
    @Override
    public List<Pedido> findByEstadoPedido(EstadoPedido estado) {
        return pedidoRepository.findByEstadoPedido(estado);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate) {
        return pedidoRepository.contarPedidosEnRango(initialDate, endDate);
    }
}
