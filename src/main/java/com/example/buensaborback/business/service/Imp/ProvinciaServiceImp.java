package com.example.buensaborback.business.service.Imp;


import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.ProvinciaService;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImp extends BaseServiceImp<Provincia,Long> implements ProvinciaService {
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Override
    public List<Provincia> findByPaisId(Long id) {
        return provinciaRepository.findByPaisId(id);
    }
}
