package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulosManufacturados")
@CrossOrigin("*")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto,Long, ArticuloManufacturadoFacadeImp> {

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }

    @PostMapping("/create")
    public ResponseEntity<ArticuloManufacturadoDto> create(@RequestBody ArticuloManufacturadoCreateDto dto) {
        return ResponseEntity.ok().body(facade.create(dto));
    }

    @PutMapping("/changeHabilitado/{id}")
    public ResponseEntity<?> changeHabilitado(@PathVariable Long id){
        facade.changeHabilitado(id);
        return ResponseEntity.ok().body("Se cambio el estado del Articulo Manufacturado");
    }
}
