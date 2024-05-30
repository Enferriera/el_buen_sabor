package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.CategoriaFacadeImp;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController extends BaseControllerImp<Categoria, CategoriaGetDto, CategoriaGetDto, Long, CategoriaFacadeImp> {

    public CategoriaController(CategoriaFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/categoriasInsumos")
    public ResponseEntity<List<CategoriaGetDto>> getCategoriaInsumos() {
        //logger.info("INICIO GET ALL categorias de insumos");
        return ResponseEntity.ok(facade.getCategoriaInsumos());
    }

    @GetMapping("/categoriasManufacturados")
    public ResponseEntity<List<CategoriaGetDto>> getCategoriaManufacturados() {
        //logger.info("INICIO GET ALL categoria articulos manufacturados");
        return ResponseEntity.ok(facade.getCategoriaManufacturados());
    }

    @DeleteMapping("/baja/{id}")
    public void deleteById(@PathVariable Long id, @RequestBody SucursalShortDto sucursal) {
        facade.deleteInSucursales(id, sucursal);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaGetDto> createNew(@RequestBody CategoriaPostDto categoriaDto) {
        return ResponseEntity.ok(facade.createNew(categoriaDto));
    }
}
