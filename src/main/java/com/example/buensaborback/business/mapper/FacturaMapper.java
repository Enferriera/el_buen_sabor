package com.example.buensaborback.business.mapper;

import com.example.buensaborback.domain.dto.FacturaDto;
import com.example.buensaborback.domain.entities.Factura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses={DetalleFacturaMapper.class})
public interface FacturaMapper extends BaseMapper<Factura, FacturaDto, FacturaDto> {
}
