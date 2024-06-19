package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloInsumoService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockCreateSucursalDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses={SucursalMapper.class, SucursalService.class,ArticuloInsumoMapper.class, ArticuloInsumoService.class})
public interface StockInsumoSucursalMapper extends BaseMapper<StockInsumoSucursal, StockInsumoShortDto,StockInsumoShortDto>{
@Mappings ({
    @Mapping(source="idSucursal", target = "sucursal", qualifiedByName = "getById"),
    @Mapping(source="idArticuloInsumo", target = "articuloInsumo", qualifiedByName = "getById")})
            public StockInsumoSucursal toCreateEntity(StockCreateSucursalDto source);
}
