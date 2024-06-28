package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.personaDto.PersonaDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaShortDto;
import com.example.buensaborback.domain.entities.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface PersonaMapper extends BaseMapper<Persona, PersonaDto, PersonaDto>{
    PersonaShortDto toShortDto(Persona persona);

}
