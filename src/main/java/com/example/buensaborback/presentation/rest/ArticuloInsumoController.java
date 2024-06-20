package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulosInsumos")
@CrossOrigin("*")
public class ArticuloInsumoController extends BaseControllerImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto,Long, ArticuloInsumoFacadeImp> {

    public ArticuloInsumoController(ArticuloInsumoFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/buscar/elaborados")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarTrue() {
        //logger.info("INICIO GET ALL insumos PARA ELABORAR");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarTrue());
    }

    @GetMapping("/buscar/noElaborados")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarFalse() {
        //logger.info("INICIO GET ALL insumos (gaseosas)");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarFalse());
    }

    @PostMapping("/create")
    public ResponseEntity<ArticuloInsumoDto> create(@RequestBody ArticuloInsumoCreateDto dto) {
        return ResponseEntity.ok().body(facade.create(dto));
    }

    @PutMapping("/changeHabilitado/{id}")
    public ResponseEntity<?> changeHabilitado(@PathVariable Long id){
        facade.changeHabilitado(id);
        return ResponseEntity.ok().body("Se cambio el estadoPedido del Insuomo");
    }

}
