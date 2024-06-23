package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class,UsuarioClienteMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto, ClienteDto> {

}
