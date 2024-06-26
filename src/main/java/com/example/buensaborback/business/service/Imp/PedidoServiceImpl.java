package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.*;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.Rol;
import com.example.buensaborback.domain.enums.TipoEnvio;
import com.example.buensaborback.repositories.FacturaRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import com.example.buensaborback.repositories.StockInsumoSucursalRepository;
import com.itextpdf.io.IOException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class PedidoServiceImpl extends BaseServiceImp<Pedido, Long> implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;

    @Autowired
    private FacturaService facturaService;
    @Autowired
    private StockInsumoSucursalRepository stockInsumoSucursalRepository;

    @Autowired
    private EmailServiceImpl emailServiceImpl;






    @Override
    public List<Pedido> obtenerPedidosEnCocina(Long idSucursal) {
        return pedidoRepository.findByEstadoPedidoAndSucursalId(EstadoPedido.PREPARACION, idSucursal);
    }

    @Override
    public List<Pedido> obtenerPedidosEnDelivery(Long idSucursal){
        return pedidoRepository.findByEstadoPedidoAndSucursalId(EstadoPedido.EN_CAMINO,idSucursal);
    }

    @Override
    public List<Pedido> buscarPedidosIngresoCaja(Long idSucursal){
        return pedidoRepository.buscarPedidosIngresoCaja(idSucursal);
    }

    @Override
    public List<Pedido> buscarPedidosPendienteEntrega(Long idSucursal){
        return pedidoRepository.findByEstadoPedidoAndSucursalId(EstadoPedido.PENDIENTE_ENTREGA,idSucursal);
    }
    private StockInsumoSucursal obtenerStockInsumoSucursal(ArticuloInsumo insumo, Sucursal sucursal) {
        return sucursal.getStocksSucursal()
                .stream()
                .filter(stock -> stock.getArticuloInsumo().equals(insumo))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró stock para el insumo: " + insumo.getDenominacion() + " en la sucursal: " + sucursal.getNombre()));
    }

    @Override
    public void revertirStock(Pedido pedido) {
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Articulo articulo = detalle.getArticulo();
            int cantidadRequerida = detalle.getCantidad();

            if (articulo instanceof ArticuloInsumo) {
                ArticuloInsumo insumo = (ArticuloInsumo) articulo;
                StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                // Incrementar el stock
                stock.setStockActual(stock.getStockActual() + cantidadRequerida);
                stockInsumoSucursalRepository.save(stock);

            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;

                for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                    ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                    int cantidadNecesaria = detalleManufacturado.getCantidad() * cantidadRequerida;
                    StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                    // Incrementar el stock
                    stock.setStockActual(stock.getStockActual() + cantidadNecesaria);
                    stockInsumoSucursalRepository.save(stock);
                }
            } else if (detalle.getPromocion() != null) {
                Promocion promocion = detalle.getPromocion();

                for (PromocionDetalle promocionDetalle : promocion.getPromocionDetalles()) {
                    Articulo articuloPromocion = promocionDetalle.getArticulo();
                    if (articuloPromocion instanceof ArticuloInsumo) {
                        ArticuloInsumo insumo = (ArticuloInsumo) articuloPromocion;
                        StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                        // Incrementar el stock
                        stock.setStockActual(stock.getStockActual() + detalle.getCantidad() * promocionDetalle.getCantidad());
                        stockInsumoSucursalRepository.save(stock);
                    } else if (articuloPromocion instanceof ArticuloManufacturado) {
                        ArticuloManufacturado manufacturado = (ArticuloManufacturado) articuloPromocion;

                        for (ArticuloManufacturadoDetalle detalleManufacturado : manufacturado.getArticuloManufacturadoDetalles()) {
                            ArticuloInsumo insumo = detalleManufacturado.getArticuloInsumo();
                            StockInsumoSucursal stock = obtenerStockInsumoSucursal(insumo, pedido.getSucursal());

                            // Incrementar el stock
                            stock.setStockActual(stock.getStockActual() + detalle.getCantidad() * promocionDetalle.getCantidad() * detalleManufacturado.getCantidad());
                            stockInsumoSucursalRepository.save(stock);
                        }
                    } else {
                        throw new RuntimeException("Tipo de artículo desconocido en Promoción: " + articuloPromocion.getClass().getName());
                    }
                }
            } else {
                throw new RuntimeException("Tipo de artículo desconocido: " + articulo.getClass().getName());
            }
        }
    }

    @Override
    @Transactional
    public Pedido updateEstado(Long id, EstadoPedido estado)  {
        Optional<Pedido> optionalPedido = baseRepository.findById(id);
        if (optionalPedido.isEmpty()) {
            throw new RuntimeException("No se encontro el pedido con el id dado.");
        }
        if (estado == null) {
            throw new RuntimeException("El estado nuevo no puede ser nulo.");
        }

        Pedido pedido = optionalPedido.get();
        EstadoPedido estadoAnterior = pedido.getEstadoPedido();
        switch (pedido.getFormaPago()) {
            case EFECTIVO -> pedido=updateEstadoEfectivo(estado, pedido);
            case MERCADO_PAGO -> pedido=updateEstadoMercadoPago(estado, pedido);
        }

   /*     try {
            emailServiceImpl.sendEmail(
                    pedido.getCliente().getUsuarioCliente().getEmail(),
                    "Actualizacion pedido Buen Sabor",
                    "Tu pedido a cambiado de estado: " + estadoAnterior + " -> " + estado
            );
        } catch (Exception e) {
            log.error(e);
        }*/
        return pedido;
    }

    public Pedido updateEstadoEfectivo(EstadoPedido newEstado, Pedido pedido)  {
        if (!pedido.getEstadoPedido().isValidNextState(newEstado, FormaPago.EFECTIVO)) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos en EFECTIVO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if (pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if (pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstadoPedido().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, COMPLETADO -> {
                pedido.setEstadoPedido(EstadoPedido.COMPLETADO);
                if(pedido.getFactura() == null) {
                   facturaService.saveFacturaAfterPagoEfectivo(pedido);


                   try {
                        // creamos la factura  la factura PDF
                        byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                        // traemos el email del cliente
                        String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                        // Enviar el email con la factura
                        emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Factura de Pedido " + pedido.getId(), "Adjunto encontrará la factura de su pedido.", "factura_" + pedido.getId() + ".pdf");

                    } catch (IOException | java.io.IOException e) {
                        throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                    }
                }else{
                    pedidoRepository.save(pedido);
                }

            }
            case PENDIENTE_PAGO, PENDIENTE_ENTREGA, PREPARACION, EN_CAMINO -> {
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }
            case CANCELADO -> {
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }
            case NOTA_CREDITO -> {
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
                try {
                    // creamos la factura  la factura PDF
                    byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                    // traemos el email del cliente
                    String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                    // Enviar el email con la factura
                    emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Nota de credito de Pedido " + pedido.getId(), "Adjunto encontrará la nota de Credito de su pedido.", "factura_" + pedido.getId() + ".pdf");

                } catch (IOException | java.io.IOException e) {
                    throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                }

            }
            default -> throw new RuntimeException("Invalid state");
        }
        return pedido;
    }

    public Pedido updateEstadoMercadoPago(EstadoPedido newEstado, Pedido pedido)  {
        if (!pedido.getEstadoPedido().isValidNextState(newEstado, FormaPago.MERCADO_PAGO)) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos de MERCADO_PAGO");
        }

        //TODO: incluir estas nuevas validaciones en el enum (va a ser necesario pasar como parametro el tipo envio)
        if (pedido.getTipoEnvio().equals(TipoEnvio.TAKE_AWAY) && newEstado.equals(EstadoPedido.EN_CAMINO)) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos TAKE_AWAY");
        }

        if (pedido.getTipoEnvio().equals(TipoEnvio.DELIVERY) && pedido.getEstadoPedido().equals(EstadoPedido.PENDIENTE_ENTREGA) && (newEstado.equals(EstadoPedido.PAGADO) || newEstado.equals(EstadoPedido.COMPLETADO))) {
            throw new RuntimeException(pedido.getEstadoPedido() + " -> " + newEstado + " es una transicion de estados invalida en pedidos DELIVERY");
        }

        switch (newEstado) {
            case PAGADO, PREPARACION, PENDIENTE_ENTREGA, EN_CAMINO, COMPLETADO -> {
                pedido.setEstadoPedido(newEstado);

                if(pedido.getFactura() == null) {
                    facturaService.saveFacturaAfterPagoEfectivo(pedido);

                    try {
                        // creamos la factura  la factura PDF
                        byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                        // traemos el email del cliente
                        String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                        // Enviar el email con la factura
                        emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Factura de Pedido " + pedido.getId(), "Adjunto encontrará la factura de su pedido.", "factura_" + pedido.getId() + ".pdf");

                    } catch (IOException | java.io.IOException e) {
                        throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                    }
                }else{
                    pedidoRepository.save(pedido);
                }


            }
            case NOTA_CREDITO -> {
                if (pedido.getEstadoPedido().equals(EstadoPedido.PAGADO)) {
                    this.revertirStock(pedido);
                }
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
                try {
                    // creamos la factura  la factura PDF
                    byte[] facturaPdf = facturaService.generarFacturaPDF(pedido);

                    // traemos el email del cliente
                    String emailCliente = pedido.getCliente().getUsuarioCliente().getEmail();

                    // Enviar el email con la factura
                    emailServiceImpl.sendMail(facturaPdf, emailCliente, null, "Nota de credito de Pedido " + pedido.getId(), "Adjunto encontrará la nota de Credito de su pedido.", "factura_" + pedido.getId() + ".pdf");

                } catch (IOException | java.io.IOException e) {
                    throw new RuntimeException("Error al generar o enviar la factura: " + e.getMessage(), e);
                }
            }
            case PENDIENTE_PAGO ->
                    throw new RuntimeException("Estado PENDIENTE_PAGO en pedidos de MERCADO_PAGO no implementado a traves de metodo updateEstado.");
            case CANCELADO ->{
                this.revertirStock(pedido);
                pedido.setEstadoPedido(newEstado);
                pedidoRepository.save(pedido);
            }

            default -> throw new RuntimeException("Estado seleccionado invalido.");
        }

        return pedido;
    }

    @Override
    public  List<Pedido> findByEstadoPedidoAndSucursalId(EstadoPedido estado,Long idSucursal){
        return pedidoRepository.findByEstadoPedidoAndSucursalId(estado, idSucursal);
    }
    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.getById(id);
    }

    @Override
    public Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate) {
        return pedidoRepository.contarPedidosEnRango(initialDate, endDate);
    }

    @Override
    public List<Pedido> findPedidoBySucursalId(LocalDate fechaInicio,LocalDate fechaFin,Long idSucursal) {
        return pedidoRepository.findPedidosBySucursalId(fechaInicio,fechaFin,idSucursal);
    }
}
