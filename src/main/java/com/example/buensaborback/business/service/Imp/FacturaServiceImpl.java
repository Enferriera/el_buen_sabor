package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.mapper.FacturaMapper;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.FacturaService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.repositories.DetallePedidoRepository;
import com.example.buensaborback.repositories.FacturaRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacturaServiceImpl extends BaseServiceImp<Factura,Long> implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private PedidoRepository    pedidoRepository;

    @Autowired
    private DetalleFacturaServiceImpl   detalleFacturaService;

    @Autowired
    private FacturaMapper facturaMapper;

    @Override
    public byte[] generarFacturaPDF(Pedido pedido) throws IOException {
        ClassPathResource pdfTemplateResource = new ClassPathResource("template/facturaBuenSabor.pdf");
        InputStream inputStream = pdfTemplateResource.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReader reader = new PdfReader(inputStream);
        PdfWriter writer = new PdfWriter(baos);

        // Crear documento PDF
        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        // Obtener la primera página del PDF
        PdfPage page = pdfDoc.getFirstPage();

        // Obtener el lienzo de la página para dibujar
        PdfCanvas canvas = new PdfCanvas(page);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Nombre del cliente
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(120, 625) // Ajustado para subir el texto
                .showText(" " + pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido())
                .endText();
        // Domicilio del cliente
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(123, 600) // Ajustado para subir el texto
                .showText(pedido.getDomicilio().getCalle() + " " + pedido.getDomicilio().getNumero())
                .endText();
        // Teléfono del cliente
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(400, 600) // Ajustado para subir el texto
                .showText(pedido.getCliente().getTelefono())
                .endText();
        // Fecha del pedido
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(115, 750) // Ajustado para subir el texto
                .showText(String.valueOf(pedido.getFechaPedido()))
                .endText();
        // Nombre de la empresa
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(400, 625) // Ajustado para subir el texto
                .showText(pedido.getSucursal().getEmpresa().getNombre())
                .endText();

        // Detalles del pedido
        int y = 530; // Posición inicial en Y para los detalles
        Set<DetallePedido> detalles = pedido.getDetallePedidos();
        for (DetallePedido detalle : detalles) {
            // Cantidad
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(90, y)
                    .showText(String.valueOf(detalle.getCantidad()))
                    .endText();

            // Descripción del artículo o promoción
            String descripcion;
            double precioUnitario;
            if (detalle.getArticulo() != null) {
                descripcion = detalle.getArticulo().getDenominacion();
                precioUnitario = detalle.getArticulo().getPrecioVenta();
            } else if (detalle.getPromocion() != null) {
                descripcion = detalle.getPromocion().getDenominacion();
                precioUnitario = detalle.getPromocion().getPrecioPromocional();
            } else {
                descripcion = "Desconocido";
                precioUnitario = 0.0;
            }

            // Descripción
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(123, y)
                    .showText(descripcion)
                    .endText();
            // Precio unitario
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, y)
                    .showText(String.valueOf(precioUnitario))
                    .endText();

            y -= 25; // Decrementar la posición en Y para el siguiente detalle

            // Si es una promoción, agregar los detalles de la promoción
            if (detalle.getPromocion() != null) {
                for (PromocionDetalle promocionDetalle : detalle.getPromocion().getPromocionDetalles()) {
                    // Detalle de la promoción (nombre del artículo en la promoción y cantidad)
                    canvas.beginText()
                            .setFontAndSize(font, 15)
                            .moveText(123, y)
                            .showText("  - " + promocionDetalle.getArticulo().getDenominacion() + " x " + promocionDetalle.getCantidad())
                            .endText();
                    y -= 20; // Decrementar la posición en Y para el detalle de la promoción y espacio adicional
                }
                y -= 25; // Espacio adicional después de los detalles de la promoción
            }
        }

        // Total
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(460, 110) // Ajustado para bajar el texto
                .showText(String.valueOf(pedido.getTotal()))
                .endText();

        // Cerrar el documento
        pdfDoc.close();

        return baos.toByteArray();
    }


    @Override
    @Transactional
    public Factura saveFacturaAfterPagoEfectivo(Pedido pedido) {
        if(facturaRepository.existsByPedidoId(pedido.getId())) {
            throw new RuntimeException("Ya existe una factura para el pedido dado.");
        }

        Factura factura = new Factura();
        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setTotalVenta(pedido.getTotal());
        factura.setFormaPago(FormaPago.EFECTIVO);

        //facturaRepository.save(factura);

        List<DetallePedido> detallesPedidos = detallePedidoRepository.findAllByPedidoId(pedido.getId());
        Set<DetalleFactura> detallesFactura = new HashSet<>();
        for(DetallePedido detallePedido : detallesPedidos) {
            DetalleFactura detalleFactura = detalleFacturaService.saveDetalleFromPedido(detallePedido);
            detallesFactura.add(detalleFactura);
        }
        factura.setDetalleFacturas(detallesFactura);

        Factura savedFactura = facturaRepository.save(factura);
        pedido.setFactura(savedFactura);
        pedidoRepository.save(pedido);
        return savedFactura;
    }

    @Override
    public Factura crearNotaCredito(Pedido pedido)  {
        Optional<Factura> optionalFactura = facturaRepository.findByPedidoId(pedido.getId());
        if(optionalFactura.isEmpty()) {
            throw new RuntimeException("No se encontro factura para el pedido dado.");
        }

        Factura factura = optionalFactura.get();


        return factura;
    }
}
