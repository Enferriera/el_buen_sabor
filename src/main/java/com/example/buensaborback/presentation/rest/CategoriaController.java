package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.CategoriaFacadeImp;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaDto;
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
@CrossOrigin(origins="*")
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

    @DeleteMapping("/baja/{idCategoria}/{idSucursal}")
    public void deleteById(@PathVariable Long idCategoria, @PathVariable Long idSucursal) {
        facade.deleteCategoriaInSucursales(idCategoria, idSucursal);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoriaGetDto> createNew(@RequestBody CategoriaPostDto categoriaDto) {
        return ResponseEntity.ok(facade.createNew(categoriaDto));
    }

    @GetMapping("/categoriasInsumoPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getCategoriaInsumoBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findCategoriasInsumoBySucursalId(idSucursal));
    }

    @GetMapping("/categoriasManufacturadoPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaDto>> getCategoriaManufacturadoBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findCategoriasManufacturadoBySucursalId(idSucursal));
    }

    @GetMapping("/allCategoriasPorSucursal/{idSucursal}")
    public ResponseEntity<List<CategoriaGetDto>> getAllCategoriaBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findAllCategoriasBySucursalId(idSucursal));
    }
}
