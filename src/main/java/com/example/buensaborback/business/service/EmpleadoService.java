package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;

import java.util.List;

public interface EmpleadoService extends BaseService<Empleado,Long> {

    Empleado findByEmail(String email);
    int contarPorRol(Rol rol);
    List<Empleado> findAllBySucursalId(Long id);
}
