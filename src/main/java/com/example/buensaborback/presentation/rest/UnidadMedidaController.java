package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.UnidadMedidaFacadeImp;
import com.example.buensaborback.domain.dto.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unidadesMedidas")
@CrossOrigin("*")
public class UnidadMedidaController extends BaseControllerImp<UnidadMedida, UnidadMedidaDto,UnidadMedidaDto,Long, UnidadMedidaFacadeImp> {

    public UnidadMedidaController(UnidadMedidaFacadeImp facade) {
        super(facade);
    }
}
