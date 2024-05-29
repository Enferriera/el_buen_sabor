package com.example.buensaborback.business.service;

import com.example.buensaborback.domain.dto.ImagenArticuloDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagenArticuloService {

    ResponseEntity<List<ImagenArticuloDto>> getAllImages();

    ResponseEntity<String> uploadImages(MultipartFile[] files, Long idArticulo);

    ResponseEntity<String> deleteImage(String publicId, Long id);
}
