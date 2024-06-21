package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, SucursalMapper.class, SucursalService.class, CategoriaService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaGetDto, CategoriaGetDto>{


    List<CategoriaDto> toDTOList(List<Categoria> source);

    @Override
    CategoriaGetDto toDTO(Categoria source);



    @Mappings({
            @Mapping(source = "idSucursales", target = "sucursales", qualifiedByName = "getById",defaultExpression = "java(new java.util.HashSet<>())"),
            @Mapping(source = "idCategoriaPadre", target = "categoriaPadre", qualifiedByName = "getById",defaultExpression = "java(null)"),
    })
    Categoria toEntityCreate(CategoriaPostDto source);


}
