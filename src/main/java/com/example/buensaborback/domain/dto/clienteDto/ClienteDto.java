package com.example.buensaborback.domain.dto.clienteDto;

import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteDto extends PersonaDto {
    private Set<DomicilioDto> domicilios = new HashSet<>();

    private Set<PedidoDto> pedidos = new HashSet<>();
}
