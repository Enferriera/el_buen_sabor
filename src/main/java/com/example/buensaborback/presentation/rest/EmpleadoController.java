package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.EmpleadoFacadeImp;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoCreateDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoSinUsuarioDto;
import com.example.buensaborback.domain.dto.empleadoDto.EmpleadoUpdateDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins="*")
public class EmpleadoController extends BaseControllerImp<Empleado, EmpleadoDto,EmpleadoDto,Long, EmpleadoFacadeImp> {
    public EmpleadoController(EmpleadoFacadeImp facade) {
        super(facade);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/empleadosPorSucursal/{idSucursal}")
    public ResponseEntity<List<EmpleadoDto>> getEMpleadosPorSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllBySucursalId(idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PostMapping("/createEmpleado")
    public ResponseEntity<EmpleadoDto> createEmpleado(@RequestBody EmpleadoCreateDto empleadoDto) {
        return ResponseEntity.ok().body(facade.createEmpleado(empleadoDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @DeleteMapping("/bajaEmpleado/{id}")
    public void deleteEmpleado(@PathVariable Long id) {
        facade.deleteEmpleado(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PutMapping("/updateEmpleado/{id}")
    public ResponseEntity<EmpleadoDto> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoUpdateDto empleadoDto) {
        return ResponseEntity.ok().body(facade.update(empleadoDto, id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO','CAJERO','DELIVERY')")
    @GetMapping("/getByAuth0ID/{auth0Id}")
    public ResponseEntity<EmpleadoSinUsuarioDto> getByAuth0ID(@PathVariable String auth0Id) {
        return ResponseEntity.ok().body(facade.findByAuth0Id(auth0Id));
    }
}
