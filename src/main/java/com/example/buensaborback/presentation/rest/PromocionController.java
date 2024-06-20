package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.Imp.PromocionFacadeImpl;

import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;

import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
@CrossOrigin(origins="*")
public class PromocionController extends BaseControllerImp<Promocion, PromocionDto, PromocionDto,Long, PromocionFacadeImpl> {
    public PromocionController(PromocionFacadeImpl facade) {
        super(facade);
    }

    @Autowired
    private PromocionFacadeImpl promocionFacade;

    @PostMapping("/create")
    public ResponseEntity<PromocionDto> create(@RequestBody PromocionCreateDto dto) {
        return ResponseEntity.ok().body(promocionFacade.create(dto));
    }

    @PutMapping("/changeHabilitado/{id}")
    public ResponseEntity<?> changeHabilitado(@PathVariable Long id){
        facade.changeHabilitado(id);
        return ResponseEntity.ok().body("Se cambio el estadoPedido del Promocion");
    }

    @GetMapping("/getHabilitados")
    public ResponseEntity<?> getHabilitados(){
        return ResponseEntity.ok().body(facade.getHabilitados());
    }

    @GetMapping("/getPromocionPorSucursal/{idSucursal}")
    public ResponseEntity<List<PromocionDto>> getPromocionPorSucursal(@PathVariable Long idSucursal ){
        return ResponseEntity.ok().body(promocionFacade.findPromocionesBySucursalId(idSucursal));
    }
}
