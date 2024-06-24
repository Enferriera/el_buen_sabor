package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaMapper.class, SucursalService.class})
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto, SucursalDto>{

    public SucursalShortDto toShortDTO(Sucursal source);

    public Sucursal shortToEntity(SucursalShortDto source);
}
