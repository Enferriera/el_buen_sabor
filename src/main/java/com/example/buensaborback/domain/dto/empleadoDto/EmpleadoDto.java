package com.example.buensaborback.domain.dto.empleadoDto;

import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpleadoDto extends PersonaDto {

    private Set<PedidoDto> pedidos= new HashSet<>();


    private SucursalDto sucursal;

    private UsuarioDto usuario;
}
