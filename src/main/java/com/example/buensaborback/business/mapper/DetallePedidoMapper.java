package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ArticuloService;
import com.example.buensaborback.domain.dto.pedidoDto.DetallePedidoDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ArticuloMapper.class, ArticuloService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoDto> {


}
