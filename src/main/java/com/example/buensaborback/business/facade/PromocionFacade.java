package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;

public interface PromocionFacade extends BaseFacade<PromocionDto,PromocionDto, Long> {

    PromocionDto create(PromocionCreateDto promocionDto);
}
