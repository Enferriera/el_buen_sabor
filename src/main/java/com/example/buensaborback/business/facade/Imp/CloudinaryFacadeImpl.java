package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.CloudinaryFacade;
import com.example.buensaborback.business.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryFacadeImpl implements CloudinaryFacade {
    @Autowired
    private CloudinaryService cloudinaryService;


    @Override
    public String uploadFile(MultipartFile file) {
        return cloudinaryService.uploadFile(file);
    }

    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        return cloudinaryService.deleteImage(publicId,id);
    }
}
