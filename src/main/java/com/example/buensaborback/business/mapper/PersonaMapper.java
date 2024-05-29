package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.PersonaDto;
import com.example.buensaborback.domain.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface PersonaMapper extends BaseMapper<Persona, PersonaDto, PersonaDto>{
}
