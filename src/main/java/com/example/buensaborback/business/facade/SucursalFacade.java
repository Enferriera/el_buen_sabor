package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SucursalFacade extends BaseFacade<SucursalDto, SucursalDto, Long> {
    SucursalDto createSucursal(SucursalDto dto);
    SucursalDto updateSucursal(Long id,SucursalDto dto);
    boolean existsSucursalByEsCasaMatriz(Long id);
    List<SucursalDto> findAllByEmpresaId( Long id);
}
