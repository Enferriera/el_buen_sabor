package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoSinUsuarioDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoUpdateDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;

import java.util.List;

public interface EmpleadoFacade extends BaseFacade<EmpleadoDto,EmpleadoDto, Long> {
   // EmpleadoDto findByEmail(String email);
    int contarPorRol(Rol rol);
    List<EmpleadoDto> findAllBySucursalId(Long id);
    EmpleadoDto createEmpleado(EmpleadoCreateDto empleadoCreateDto);
    void deleteEmpleado(Long id);

    EmpleadoDto update(EmpleadoUpdateDto empleadoDto, Long id);

    EmpleadoSinUsuarioDto findByAuth0Id(String auth0Id);
}
