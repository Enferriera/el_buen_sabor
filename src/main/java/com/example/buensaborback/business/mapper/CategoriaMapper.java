package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, SucursalMapper.class, SucursalService.class, CategoriaService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaPostDto, CategoriaGetDto>{

    @Override
    CategoriaGetDto toDTO(Categoria source);

    @Override

    @Mappings({
            @Mapping(source = "idSucursales", target = "sucursales", qualifiedByName = "getById"),
            @Mapping(source = "idCategoriaPadre", target = "categoriaPadre", qualifiedByName = "getById")
    })
    Categoria toEntity(CategoriaPostDto source);


}
