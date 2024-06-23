package com.example.buensaborback.domain.dto.empleadoDto;

import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpleadoCreateDto extends PersonaDto {
    private UsuarioDto usuario;
    private Long idSucursal;
}
