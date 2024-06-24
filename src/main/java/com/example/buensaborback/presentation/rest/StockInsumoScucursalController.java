package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.StockInsumoSucursalFacadeImpl;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockCreateSucursalDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins="*")
public class StockInsumoScucursalController extends BaseControllerImp<StockInsumoSucursal, StockInsumoShortDto,StockInsumoShortDto,Long, StockInsumoSucursalFacadeImpl> {
    public StockInsumoScucursalController(StockInsumoSucursalFacadeImpl facade) {
        super(facade);
    }


    @GetMapping("/getBySucursalId/{idSucursal}")
    public ResponseEntity<Set<StockInsumoShortDto>> getBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllBySucursalId(idSucursal));
    }

    @PostMapping("/create")
    public ResponseEntity<StockInsumoShortDto> create(@RequestBody StockCreateSucursalDto source){
        return ResponseEntity.ok().body(facade.createNew(source));
    }
}
