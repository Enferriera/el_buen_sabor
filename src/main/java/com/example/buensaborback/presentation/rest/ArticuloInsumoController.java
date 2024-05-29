package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulosInsumos")
@CrossOrigin("*")
public class ArticuloInsumoController extends BaseControllerImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto,Long, ArticuloInsumoFacadeImp> {

    public ArticuloInsumoController(ArticuloInsumoFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/paged/insumosParaElaborar")
    public ResponseEntity<Page<ArticuloInsumoDto>> findByEsParaElaborarTrue(Pageable pageable) {
        //logger.info("INICIO GET ALL insumos PARA ELABORAR");
        return ResponseEntity.ok(facade.findByEsParaElaborarTrue(pageable));
    }

    @GetMapping("/paged/insumosDirectos")
    public ResponseEntity<Page<ArticuloInsumoDto>> findByEsParaElaborarFalse(Pageable pageable) {
        //logger.info("INICIO GET ALL insumos (gaseosas)");
        return ResponseEntity.ok(facade.findByEsParaElaborarFalse(pageable));
    }
}
