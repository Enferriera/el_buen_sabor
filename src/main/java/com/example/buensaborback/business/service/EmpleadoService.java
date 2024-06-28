package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoCreateDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmpleadoService extends BaseService<Empleado,Long> {

   // Empleado findByEmail(String email);
    int contarPorRol(Rol rol);
    List<Empleado> findAllBySucursalId(Long idSucursal);
    Empleado createEmpleado(Empleado empleado);
    void deleteEmpleado(Long id);
    @Override
    Empleado update(Empleado empleado, Long id);
    Empleado findByAuth0Id(String auth0Id);
}
