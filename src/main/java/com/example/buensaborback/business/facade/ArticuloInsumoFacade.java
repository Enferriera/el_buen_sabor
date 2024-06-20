package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoShortDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;

import java.util.List;

public interface ArticuloInsumoFacade extends BaseFacade<ArticuloInsumoDto, ArticuloInsumoDto, Long> {
    public void changeHabilitado(Long id);
    public ArticuloInsumoDto create(ArticuloInsumoCreateDto articuloInsumoCreateDto);

    public List<ArticuloInsumoDto> findArticulosInsumosBySucursalId(Long idSucursal);
}
