package com.example.buensaborback.business.mapper;


import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoSinUsuarioDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoUpdateDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",uses={UsuarioMapper.class, SucursalMapper.class, SucursalService.class,PersonaMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoDto> {
@Mappings({
        @Mapping(source = "idSucursal", target = "sucursal", qualifiedByName = "getById"),
        @Mapping(source = "usuario.rol",target = "usuario.rol", qualifiedByName = "stringToRol")
})
    Empleado toCreateEntity(EmpleadoCreateDto dto);

    @Mappings({
            @Mapping(source = "idSucursal", target = "sucursal", qualifiedByName = "getById"),
            @Mapping(source = "usuario.rol",target = "usuario.rol", qualifiedByName = "stringToRol")
    })
    Empleado toUpdateEntity(EmpleadoUpdateDto dto);

    EmpleadoSinUsuarioDto toEmpleadoSinUsuarioDto(Empleado empleado);

    @Named("stringToRol")
    default Rol stringToRol(String rol) {
        return Rol.valueOf(rol.toUpperCase());
    }
    @Named("rolToString")
    default String rolToString(Rol rolString) {
        return rolString.name();
    }
}
