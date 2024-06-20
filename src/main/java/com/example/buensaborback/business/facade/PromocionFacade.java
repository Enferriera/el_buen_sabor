package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;

import java.util.List;

public interface PromocionFacade extends BaseFacade<PromocionDto,PromocionDto, Long> {

    PromocionDto create(PromocionCreateDto promocionDto);

    public void changeHabilitado(Long id);

    public List<PromocionDto> getHabilitados();

    public List<PromocionDto> findPromocionesBySucursalId(Long idSucursal);
}
