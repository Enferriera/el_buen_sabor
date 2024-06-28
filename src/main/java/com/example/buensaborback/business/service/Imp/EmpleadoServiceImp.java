package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.business.service.EmpleadoService;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.domain.enums.Rol;
import com.example.buensaborback.repositories.EmpleadoRepository;
import com.example.buensaborback.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImp extends BaseServiceImp<Empleado,Long> implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
/*
    @Override
    public Empleado findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

 */

    @Override
    public Empleado findByAuth0Id(String auth0Id) {
        return usuarioRepository.findByAuth0Id(auth0Id);
    }

    @Override
    public Empleado update(Empleado empleado, Long id) {
        Empleado empleadoBase= empleadoRepository.getById(id);
        empleado.setId(empleadoBase.getId());
        return empleadoRepository.save(empleado);
    }

    @Override
    public int contarPorRol(Rol rol) {
        return empleadoRepository.countByUsuarioRol(rol);
    }

    @Override
    public List<Empleado> findAllBySucursalId(Long id) {
        return empleadoRepository.findAllBySucursalId(id);
    }

    @Override
    @Transactional
    public Empleado createEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void deleteEmpleado(Long id) {
        Empleado empleado = empleadoRepository.getById(id);
        Usuario usuario = usuarioRepository.getById(empleado.getUsuario().getId());
        empleadoRepository.delete(empleado);
        usuarioRepository.delete(usuario);
    }
}
