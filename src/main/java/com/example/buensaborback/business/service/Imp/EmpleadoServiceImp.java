package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.business.service.EmpleadoService;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import com.example.buensaborback.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImp extends BaseServiceImp<Empleado,Long> implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public Empleado findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public int contarPorRol(Rol rol) {
        return empleadoRepository.countByRol(rol);
    }

    @Override
    public List<Empleado> findAllBySucursalId(Long id) {
        return empleadoRepository.findAllBySucursalId(id);
    }
}
