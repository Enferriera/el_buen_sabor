package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.DomicilioFacadeImp;
import com.example.buensaborback.business.facade.Imp.EmpleadoFacadeImp;
import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.dto.EmpleadoDto;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin("*")
public class EmpleadoController extends BaseControllerImp<Empleado, EmpleadoDto,EmpleadoDto,Long, EmpleadoFacadeImp> {
    public EmpleadoController(EmpleadoFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/empleadosPorSucursal/{idSucursal}")
    public ResponseEntity<List<EmpleadoDto>> getEMpleadosPorSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllBySucursalId(idSucursal));
    }
}
