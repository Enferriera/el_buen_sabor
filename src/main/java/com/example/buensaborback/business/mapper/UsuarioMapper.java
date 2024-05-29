package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto, UsuarioDto>{

}
