package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.EmpleadoFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.EmpleadoMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.EmpleadoService;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoSinUsuarioDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoUpdateDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoFacadeImp extends BaseFacadeImp<Empleado, EmpleadoDto,EmpleadoDto, Long> implements EmpleadoFacade {
    public EmpleadoFacadeImp(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDto, EmpleadoDto> baseMapper) {
        super(baseService, baseMapper);
    }
@Autowired
private EmpleadoMapper  empleadoMapper;
    @Autowired
    private EmpleadoService empleadoService;
   /* @Override
    public EmpleadoDto findByEmail(String email) {
        return empleadoMapper.toDTO(empleadoService.findByEmail(email));
    }*/

    @Override
    public int contarPorRol(Rol rol) {
        return empleadoService.contarPorRol(rol);
    }

    @Override
    public List<EmpleadoDto> findAllBySucursalId(Long id) {
        return empleadoMapper.toDTOsList(empleadoService.findAllBySucursalId(id));
    }

    @Override
    public EmpleadoDto createEmpleado(EmpleadoCreateDto empleadoDto) {
        return empleadoMapper.toDTO(empleadoService.createEmpleado(empleadoMapper.toCreateEntity(empleadoDto)));
    }

    @Override
    public void deleteEmpleado(Long id) {
        empleadoService.deleteEmpleado(id);
    }

    @Override
    public EmpleadoDto update(EmpleadoUpdateDto empleadoDto, Long id) {
        return empleadoMapper.toDTO(empleadoService.update(empleadoMapper.toUpdateEntity(empleadoDto), id));
    }

    @Override
    public EmpleadoSinUsuarioDto findByAuth0Id(String auth0Id) {
        return empleadoMapper.toEmpleadoSinUsuarioDto(empleadoService.findByAuth0Id(auth0Id));
    }
}
