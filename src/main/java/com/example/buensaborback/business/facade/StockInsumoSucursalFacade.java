package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockCreateSucursalDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;

import java.util.Set;

public interface StockInsumoSucursalFacade extends BaseFacade<StockInsumoShortDto,StockInsumoShortDto,Long> {

    Set<StockInsumoShortDto> findAllBySucursalId(Long idSucursal);

    StockInsumoShortDto createNew(StockCreateSucursalDto source);
}
