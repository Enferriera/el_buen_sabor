package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.business.service.UnidadMedidaService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.*;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses={ArticuloInsumoMapper.class, SucursalService.class, CategoriaService.class, CategoriaMapper.class, UnidadMedidaService.class, UnidadMedidaMapper.class, ArticuloMapper.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto>{
    @Mappings({
            @Mapping(source = "idUnidadMedida", target = "unidadMedida", qualifiedByName = "getById"),
            @Mapping(source = "idCategoria", target = "categoria", qualifiedByName = "getById"),

    })
    public ArticuloInsumo toCreateEntity(ArticuloInsumoCreateDto source);

    @Mapping(source="denominacion", target = "denominacion")
    public ArticuloInsumoShortDto toShortDTO(ArticuloInsumo source);

    default Set<StockInsumoSucursal> mapStocks(Set<StockCreateSucursalDto> stockDtos) {
        if (stockDtos == null) {
            return null;
        }
        return stockDtos.stream()
                .map(this::mapStock)
                .collect(Collectors.toSet());
    }

    @Mapping(source = "idSucursal", target = "sucursal", qualifiedByName = "getById")
    StockInsumoSucursal mapStock(StockCreateSucursalDto stockDto);

    List<ArticuloInsumoShortDto>  toDtoList(List<ArticuloInsumo> source);

}
