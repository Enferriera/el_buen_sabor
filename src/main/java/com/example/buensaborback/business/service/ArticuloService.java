package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import com.example.buensaborback.domain.entities.Articulo;
import org.mapstruct.Named;

public interface ArticuloService extends BaseService<Articulo,Long> {
    @Named("mapIdToArticulo")
    Articulo mapIdToArticulo(Long idArticulo);
}
