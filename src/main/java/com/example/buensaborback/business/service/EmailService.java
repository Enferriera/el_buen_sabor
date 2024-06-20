package com.example.buensaborback.business.service;

public interface EmailService {

    String sendMail(byte[] file, String to, String[] cc, String subject, String body, String attachmentFilename);
}
