package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.CloudinaryFacade;
import com.example.buensaborback.business.facade.ImagenArticuloFacade;
import com.example.buensaborback.domain.dto.ImagenArticuloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins="*")
public class ImageController {

    @Autowired
    private CloudinaryFacade cloudinaryFacade;


    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(@RequestParam("uploads") MultipartFile file) {
        return ResponseEntity.ok().body(cloudinaryFacade.uploadFile(file));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(@RequestParam("publicId") String publicId, @RequestParam("id") Long id) {
        return cloudinaryFacade.deleteImage(publicId, id);
    }
}
