package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;

import java.util.List;

public interface ArticuloManufacturadoFacade extends BaseFacade<ArticuloManufacturadoDto, ArticuloManufacturadoDto, Long> {
public ArticuloManufacturadoDto create(ArticuloManufacturadoCreateDto articuloManufacturadoCreateDto);
    public void changeHabilitado(Long id);

    public List<ArticuloManufacturadoDto> getHabilitados();
}
