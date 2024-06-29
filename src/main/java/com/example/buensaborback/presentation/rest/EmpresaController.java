package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.EmpresaFacadeImpl;
import com.example.buensaborback.domain.dto.EmpresaDto;
import com.example.buensaborback.domain.dto.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins="*")
public class EmpresaController extends BaseControllerImp<Empresa, EmpresaDto,EmpresaDto, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PutMapping("/addSucursal")
    public ResponseEntity<EmpresaLargeDto> addSucursal(@RequestParam Long idEmpresa, @RequestParam Long idSucursal){
        return ResponseEntity.ok(facade.addSucursal(idEmpresa,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/full/{idEmpresa}")
    public ResponseEntity<EmpresaLargeDto> getEmpresaSucursales(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(facade.findWithSucursalesById(idEmpresa));
    }


}
