package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloService;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDetalleCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, ArticuloService.class})
public interface PromocionDetalleMapper extends BaseMapper<PromocionDetalle, PromocionDetalleDto, PromocionDetalleDto> {

       @Mapping(source="idArticulo",target="articulo",qualifiedByName = "getById")
    PromocionDetalle toCreateEntity(PromocionDetalleCreateDto source);
}
