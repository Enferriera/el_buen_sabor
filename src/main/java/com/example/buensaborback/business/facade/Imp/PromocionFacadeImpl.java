package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PromocionMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.Imp.PromocionServiceImpl;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionFacadeImpl extends BaseFacadeImp<Promocion,PromocionDto,PromocionDto,Long> implements PromocionFacade {
    private final PromocionMapper promocionMapper;
    private final PromocionServiceImpl promocionServiceImpl;
    @Autowired
    private PromocionService promocionService;

    public PromocionFacadeImpl(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionDto> baseMapper, PromocionMapper promocionMapper, PromocionServiceImpl promocionServiceImpl) {
        super(baseService, baseMapper);
        this.promocionMapper = promocionMapper;
        this.promocionServiceImpl = promocionServiceImpl;
    }

public PromocionDto create(PromocionCreateDto promocionDto) {
        System.out.println("PromocionFacadeImpl: " + promocionDto.getDenominacion());
    Promocion promocion = promocionMapper.toCreateEntity(promocionDto);
    System.out.println("PromocionFacadeImpl: " + promocion.getDenominacion());
    return promocionMapper.toDTO(baseService.create(promocion));
}

    @Override
    public void changeHabilitado(Long id) {
        promocionServiceImpl.changeHabilitado(id);
    }

    @Override
    public List<PromocionDto> getHabilitados() {
        return promocionMapper.toDTOsList(promocionService.getHabilitados());
    }

    @Override
    public List<PromocionDto> findPromocionesBySucursalId(Long idSucursal){
        return promocionMapper.toDTOsList(promocionService.findPromocionesBySucursalId(idSucursal));
    }
}

