package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.EmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;

import java.util.List;

public interface EmpleadoFacade extends BaseFacade<EmpleadoDto,EmpleadoDto, Long> {
    EmpleadoDto findByEmail(String email);
    int contarPorRol(Rol rol);
    List<EmpleadoDto> findAllBySucursalId(Long id);
}
