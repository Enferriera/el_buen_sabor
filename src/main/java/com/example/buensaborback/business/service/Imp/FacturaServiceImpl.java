package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.mapper.FacturaMapper;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.FacturaService;
import com.example.buensaborback.domain.entities.DetalleFactura;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.repositories.DetallePedidoRepository;
import com.example.buensaborback.repositories.FacturaRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
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
    private DetalleFacturaServiceImpl   detalleFacturaService;

    @Autowired
    private FacturaMapper facturaMapper;

    @Override
    public byte[] generarFacturaPDF(Pedido pedido) throws IOException {
        ClassPathResource pdfTemplateResource = new ClassPathResource("template/factura.pdf");
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

        //x=derecha,izq y=arriba abajo
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(120, 633)
                .showText(" " + pedido.getCliente().getNombre()+ " " +pedido.getCliente().getApellido() )
                .endText();
        //domicilo
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(120, 610)
                .showText(String.valueOf(pedido.getDomicilio().getCalle()+" " + pedido.getDomicilio().getNumero()))
                .endText();
        //telefono
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(400, 611)
                .showText(pedido.getCliente().getTelefono())
                .endText();
        //fecha
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(115, 760)
                .showText(String.valueOf(pedido.getFechaPedido()))
                .endText();
        //detalles
        int y = 540; // Posición inicial en Y para los detalles usar siempre el y
        Set<DetallePedido> detalles = pedido.getDetallePedidos();
        for (DetallePedido detalle : detalles) {
            //cantidad
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(90, y)
                    .showText(String.valueOf(detalle.getCantidad()))
                    .endText();
            //descripcion
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(123, y)
                    .showText( detalle.getArticulo().getDenominacion())
                    .endText();
            //precio
            canvas.beginText()
                    .setFontAndSize(font, 15)
                    .moveText(460, y)
                    .showText(String.valueOf(detalle.getArticulo().getPrecioVenta()))
                    .endText();
            y -= 25; // Decrementar la posición en Y para el siguiente detalle
        }
        //total
        canvas.beginText()
                .setFontAndSize(font, 15)
                .moveText(460, 120)
                .showText(String.valueOf(pedido.getTotal()))
                .endText();

        // Cerrar el documento
        pdfDoc.close();

        return baos.toByteArray();
    }

    @Override
    public Factura saveFacturaAfterPagoEfectivo(Pedido pedido) throws ServicioException {
        if(facturaRepository.existsByPedidoId(pedido.getId())) {
            throw new ServicioException("Ya existe una factura para el pedido dado.");
        }

        Factura factura = new Factura();

        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setTotalVenta(pedido.getTotal());
        factura.setFormaPago(FormaPago.EFECTIVO);

       factura = facturaRepository.save(factura);

        List<DetallePedido> detallesPedidos = detallePedidoRepository.findAllByPedidoId(pedido.getId());
        Set<DetalleFactura> detallesFactura= new HashSet<>();
        for(DetallePedido detallePedido : detallesPedidos) {
            detallesFactura.add(detalleFacturaService.saveDetalleFromPedido(detallePedido));
        }
        factura.setDetalleFacturas(detallesFactura);

        factura = facturaRepository.save(factura);

        return factura;
    }

    @Override
    public Factura crearNotaCredito(Pedido pedido) throws ServicioException {
        Optional<Factura> optionalFactura = facturaRepository.findByPedidoId(pedido.getId());
        if(optionalFactura.isEmpty()) {
            throw new ServicioException("No se encontro factura para el pedido dado.");
        }

        Factura factura = optionalFactura.get();


        return factura;
    }
}
