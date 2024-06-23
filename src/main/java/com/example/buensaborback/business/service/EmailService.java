package com.example.buensaborback.business.service;

import com.example.buensaborback.domain.entities.Pedido;

public interface EmailService {

    String sendMail(byte[] file, String to, String[] cc, String subject, String body, String attachmentFilename);

    public void sendAltaPedidoEmail(String to, Pedido pedido);

    public void sendEmail(String to, String subject, String body);
}