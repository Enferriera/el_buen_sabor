package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloInsumoService;
import com.example.buensaborback.domain.dto.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDetalleCreateDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ArticuloInsumoMapper.class, ArticuloInsumoService.class})
public interface ArticuloManufacturadoDetalleMapper extends BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetalleDto> {
    @Mapping(target = "articuloInsumo", source = "idArticuloInsumo",qualifiedByName = "getById")
    public ArticuloManufacturadoDetalle toCreateEntity(ArticuloManufacturadoDetalleCreateDto source);
}
