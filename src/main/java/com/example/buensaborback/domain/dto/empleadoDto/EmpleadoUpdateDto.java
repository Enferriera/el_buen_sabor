package com.example.buensaborback.domain.dto.empleadoDto;

import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;

public class EmpleadoUpdateDto extends PersonaDto{
    private SucursalShortDto sucursal;
    private UsuarioDto usuario;
}