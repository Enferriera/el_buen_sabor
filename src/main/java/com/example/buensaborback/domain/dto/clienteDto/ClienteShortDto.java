package com.example.buensaborback.domain.dto.clienteDto;

import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaShortDto;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteShortDto;
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
public class ClienteShortDto extends PersonaShortDto {
    private Set<DomicilioDto> domicilios = new HashSet<>();
    private UsuarioClienteShortDto usuarioCliente;
}
