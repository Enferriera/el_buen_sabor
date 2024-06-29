package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.Imp.SucursalFacadeImp;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
@CrossOrigin(origins="*")
public class SucursalController extends BaseControllerImp<Sucursal, SucursalDto, SucursalDto,Long, SucursalFacadeImp> {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);
    public SucursalController(SucursalFacadeImp facade) {
        super(facade);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @Override
    @PostMapping()
    public ResponseEntity<SucursalDto> create(@RequestBody SucursalDto dto) {
        return ResponseEntity.ok().body(facade.createSucursal(dto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> edit( @RequestBody SucursalDto dto,@PathVariable Long id){
       logger.info("Editing Sucursal "+id);
       logger.info("Editing Sucursal "+dto.getId());
        return ResponseEntity.ok().body(facade.updateSucursal(id, dto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/categorias/{id}")
    public List<CategoriaGetDto> getCategoriasBySucursalId(@PathVariable Long id) {
        return facade.findCategoriasBySucursalId(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/esCasaMatriz/{id}")
    public ResponseEntity<Boolean> esCasaMatriz(@PathVariable Long id) {
        return ResponseEntity.ok().body(facade.existsSucursalByEsCasaMatriz(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/porEmpresa/{id}")
    public List<SucursalDto> getSucursalesByEmpresaId(@PathVariable Long id) {
        return facade.findAllByEmpresaId(id);
    }
}
