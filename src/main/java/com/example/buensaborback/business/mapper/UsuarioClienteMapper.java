package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface UsuarioClienteMapper extends BaseMapper<UsuarioCliente, UsuarioClienteDto, UsuarioClienteDto> {
}
