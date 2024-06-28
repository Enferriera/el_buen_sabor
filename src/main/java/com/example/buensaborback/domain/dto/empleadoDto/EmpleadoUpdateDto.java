package com.example.buensaborback.domain.dto.empleadoDto;

import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmpleadoUpdateDto extends PersonaDto{
    private Long idSucursal;
    private UsuarioDto usuario;
}
