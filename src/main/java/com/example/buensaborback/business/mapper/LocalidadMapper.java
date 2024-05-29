package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.LocalidadDto;
import com.example.buensaborback.domain.entities.Localidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDto, LocalidadDto> {
}
