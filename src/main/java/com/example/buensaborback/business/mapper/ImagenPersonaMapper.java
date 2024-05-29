package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.ImagenPersonaDto;
import com.example.buensaborback.domain.dto.PaisDto;
import com.example.buensaborback.domain.entities.ImagenPersona;
import com.example.buensaborback.domain.entities.Pais;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagenPersonaMapper extends BaseMapper<ImagenPersona, ImagenPersonaDto, ImagenPersonaDto>{

}
