package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.EmpresaFacadeImpl;
import com.example.buensaborback.domain.dto.EmpresaDto;
import com.example.buensaborback.domain.dto.EmpresaLargeDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/empresas")
@CrossOrigin("*")
public class EmpresaController extends BaseControllerImp<Empresa, EmpresaDto,EmpresaDto, Long, EmpresaFacadeImpl> {
    public EmpresaController(EmpresaFacadeImpl facade) {
        super(facade);
    }

    @PutMapping("/addSucursal")
    public ResponseEntity<EmpresaLargeDto> addSucursal(@RequestParam Long idEmpresa, @RequestParam Long idSucursal){
        return ResponseEntity.ok(facade.addSucursal(idEmpresa,idSucursal));
    }

    @GetMapping("/full/{idEmpresa}")
    public ResponseEntity<EmpresaLargeDto> getEmpresaSucursales(@PathVariable Long idEmpresa) {
        return ResponseEntity.ok(facade.findWithSucursalesById(idEmpresa));
    }


}
