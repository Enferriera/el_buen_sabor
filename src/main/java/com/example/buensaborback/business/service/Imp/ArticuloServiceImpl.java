package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.ArticuloService;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl extends BaseServiceImp<Articulo, Long> implements ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public Articulo mapIdToArticulo(Long idArticulo) {
        if (idArticulo == null) {
            return null;
        }
        return articuloRepository.getById(idArticulo);
    }

}
