package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.LocalidadFacadeImp;
import com.example.buensaborback.domain.dto.LocalidadDto;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
@CrossOrigin("*")
public class LocalidadController extends BaseControllerImp<Localidad, LocalidadDto,LocalidadDto, Long, LocalidadFacadeImp> {

    public LocalidadController(LocalidadFacadeImp facade) {
        super(facade);
    }
    private static final Logger logger = LoggerFactory.getLogger(LocalidadController.class);

    @GetMapping("findByProvincia/{idProvincia}")
    public ResponseEntity<List<LocalidadDto>> getByProvincia(@PathVariable Long idProvincia) {
        logger.info("INICIO GET BY PROVINCIA");
        return ResponseEntity.ok(facade.findByProvinciaId(idProvincia));
    }
}
