package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.UnidadMedidaService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses={ArticuloInsumoMapper.class, CategoriaService.class, CategoriaMapper.class, UnidadMedidaService.class, UnidadMedidaMapper.class, ArticuloMapper.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto>{
    @Mappings({
            @Mapping(source = "idUnidadMedida", target = "unidadMedida", qualifiedByName = "getById"),
            @Mapping(source = "idCategoria", target = "categoria", qualifiedByName = "getById"),
    })
    public ArticuloInsumo toCreateEntity(ArticuloInsumoCreateDto source);
}
