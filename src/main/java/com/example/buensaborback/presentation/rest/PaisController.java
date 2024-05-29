package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.PaisFacadeImp;
import com.example.buensaborback.domain.dto.PaisDto;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
@CrossOrigin("*")
public class PaisController extends BaseControllerImp<Pais, PaisDto,PaisDto,Long, PaisFacadeImp> {

    public PaisController(PaisFacadeImp facade) {
        super(facade);
    }
}
