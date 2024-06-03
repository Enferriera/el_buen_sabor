package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PromocionMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;
import org.springframework.stereotype.Service;

@Service
public class PromocionFacadeImpl extends BaseFacadeImp<Promocion,PromocionDto,PromocionDto,Long> implements PromocionFacade {
    private final PromocionMapper promocionMapper;

    public PromocionFacadeImpl(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionDto> baseMapper, PromocionMapper promocionMapper) {
        super(baseService, baseMapper);
        this.promocionMapper = promocionMapper;
    }

public PromocionDto create(PromocionCreateDto promocionDto) {
        System.out.println("PromocionFacadeImpl: " + promocionDto.getDenominacion());
    Promocion promocion = promocionMapper.toCreateEntity(promocionDto);
    System.out.println("PromocionFacadeImpl: " + promocion.getDenominacion());
    return promocionMapper.toDTO(baseService.create(promocion));
}
}
