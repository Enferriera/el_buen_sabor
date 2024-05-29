package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.ArticuloInsumoFacade;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.mapper.ArticuloInsumoMapper;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.ArticuloInsumoService;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloInsumoFacadeImp extends BaseFacadeImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto, Long> implements ArticuloInsumoFacade {

    @Autowired
    ArticuloInsumoService articuloInsumoService;

    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;

    public ArticuloInsumoFacadeImp(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto> baseMapper) {
        super(baseService, baseMapper);
    }

    public Page<ArticuloInsumoDto> findByEsParaElaborarTrue(Pageable pageable) {
        // Trae una página de entidades
        Page<ArticuloInsumo> entities = articuloInsumoService.findByEsParaElaborarTrue(pageable);
        // Mapea las entidades a DTOs
        List<ArticuloInsumoDto> dtos = entities.getContent().stream()
                .map(articuloInsumoMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    public Page<ArticuloInsumoDto> findByEsParaElaborarFalse(Pageable pageable) {
        // Trae una página de entidades
        Page<ArticuloInsumo> entities = articuloInsumoService.findByEsParaElaborarFalse(pageable);
        // Mapea las entidades a DTOs
        List<ArticuloInsumoDto> dtos = entities.getContent().stream()
                .map(articuloInsumoMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

}
