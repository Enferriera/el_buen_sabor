package com.example.buensaborback.business.mapper;


import com.example.buensaborback.domain.dto.EmpleadoDto;
import com.example.buensaborback.domain.dto.EmpresaDto;
import com.example.buensaborback.domain.dto.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.entities.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses={UsuarioMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoDto> {

}
