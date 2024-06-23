package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.EmailService;
import com.example.buensaborback.domain.entities.Pedido;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @Override
    public void sendAltaPedidoEmail(String to, Pedido pedido) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Argentina/Buenos_Aires")));
        String strHoraEstimadaFinalizacion = dateFormat.format(pedido.getHoraEstimadaFinalizacion());
        this.sendEmail(
                to,
                "Alta pedido confirmada Buen Sabor",
                "¡Hola " + pedido.getCliente().getNombre() +" "+pedido.getCliente().getApellido()+ "!\n\n" +
                        "Tu pedido ha sido confirmado en Buen Sabor.\n" +
                        "Detalles del pedido:\n" +
                        "Hora estimada de finalización: " + strHoraEstimadaFinalizacion + "\n" +
                        "Total: $" + pedido.getTotal() + "\n" +
                        "Estado del pedido: " + pedido.getEstadoPedido() + "\n" +
                        "Tipo de envío: " + pedido.getTipoEnvio() + "\n" +
                        "Forma de pago: " + pedido.getFormaPago() + "\n" +
                        "\nGracias por elegir Buen Sabor. ¡Esperamos que disfrutes tu pedido!\n" +
                        "Atentamente,\n" +
                        "El equipo de Buen Sabor");
    }



    @Override
    public String sendMail(byte[] file, String to, String[] cc, String subject, String body, String attachmentFilename) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);

            if (cc != null && cc.length > 0) {
                mimeMessageHelper.setCc(cc);
            }

            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            if (file != null) {
                mimeMessageHelper.addAttachment(
                        attachmentFilename,
                        new ByteArrayResource(file)
                );
            }

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";

        } catch (Exception e) {
            throw new RuntimeException("Error while sending email: " + e.getMessage(), e);
        }
    }
}
