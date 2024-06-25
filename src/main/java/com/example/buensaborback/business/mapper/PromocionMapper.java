package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionShortDto;
import com.example.buensaborback.domain.entities.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ImagenPromocionMapper.class, PromocionDetalleMapper.class, SucursalMapper.class, SucursalService.class})
public interface PromocionMapper extends BaseMapper<Promocion, PromocionDto,PromocionDto>  {

     @Mappings({
             @Mapping(source="idsSucursales", target = "sucursales", qualifiedByName = "getById"),

     })
     Promocion toCreateEntity(PromocionCreateDto source);

     public PromocionShortDto toShortDTO(Promocion source);
}
