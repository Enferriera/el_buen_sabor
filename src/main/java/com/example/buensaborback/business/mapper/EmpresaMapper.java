package com.example.buensaborback.business.mapper;


import com.example.buensaborback.domain.dto.EmpresaDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.dto.EmpresaLargeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto, EmpresaDto> {
    EmpresaLargeDto toLargeDto(Empresa empresa);


}
