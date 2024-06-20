package com.example.buensaborback.business.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryFacade {

    // Método para subir un archivo a Cloudinary
    public  String uploadFile(MultipartFile file);

    // Método para eliminar una imagen de Cloudinary
    public ResponseEntity<String> deleteImage(String publicId, Long id);
}
