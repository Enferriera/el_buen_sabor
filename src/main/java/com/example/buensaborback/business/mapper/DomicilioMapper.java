package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LocalidadMapper.class)
public interface DomicilioMapper extends BaseMapper<Domicilio, DomicilioDto, DomicilioDto> {


}
