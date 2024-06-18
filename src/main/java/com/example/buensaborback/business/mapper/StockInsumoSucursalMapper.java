package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockCreateSucursalDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses={SucursalMapper.class, SucursalService.class,ArticuloInsumoMapper.class})
public interface StockInsumoSucursalMapper extends BaseMapper<StockInsumoSucursal, StockInsumoShortDto,StockInsumoShortDto>{

    @Mapping(source="idSucursal", target = "sucursal", qualifiedByName = "getById")
            public StockInsumoSucursal toCreateEntity(StockCreateSucursalDto source);
}
