package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.DetalleFacturaDto;
import com.example.buensaborback.domain.entities.DetalleFactura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses={ArticuloMapper.class})
public interface DetalleFacturaMapper extends BaseMapper<DetalleFactura, DetalleFacturaDto, DetalleFacturaDto> {

    @Override

    DetalleFacturaDto toDTO(DetalleFactura source);
}
