package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoShortDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/articulosInsumos")
@CrossOrigin(origins="*")
public class ArticuloInsumoController extends BaseControllerImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoDto,Long, ArticuloInsumoFacadeImp> {

    public ArticuloInsumoController(ArticuloInsumoFacadeImp facade) {
        super(facade);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/buscar/elaborados/{idEmpresa}")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarTrue(@PathVariable Long idEmpresa) {
        //logger.info("INICIO GET ALL insumos PARA ELABORAR");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarTrue(idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/buscar/noElaborados/{idEmpresa}")
    public ResponseEntity<List<ArticuloInsumoDto>> findByEsParaElaborarFalse(@PathVariable Long idEmpresa) {
        //logger.info("INICIO GET ALL insumos (gaseosas)");
        return ResponseEntity.ok().body(facade.findByEsParaElaborarFalse(idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PostMapping("/create")
    public ResponseEntity<ArticuloInsumoDto> create(@RequestBody ArticuloInsumoCreateDto dto) {
        return ResponseEntity.ok().body(facade.create(dto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PutMapping("/changeHabilitado/{id}")
    public ResponseEntity<?> changeHabilitado(@PathVariable Long id){
        facade.changeHabilitado(id);
        return ResponseEntity.ok().body("Se cambio el estadoPedido del Insuomo");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/getInsumosPorSucursal/{idSucursal}")
    public ResponseEntity<List<ArticuloInsumoDto>> getInsumosPorSUcursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findArticulosInsumosBySucursalId(idSucursal));
    }

    // Método POST para subir imágenes
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files,
            @RequestParam(value = "id", required = true) Long idArticulo) {
        try {
            return facade.uploadImages(files, idArticulo); // Llama al método del servicio para subir imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    // Método POST para eliminar imágenes por su publicId y Long
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "id", required = true) Long id) {
        try {
            return facade.deleteImage(publicId, id); // Llama al método del servicio para eliminar la imagen
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format"); // Respuesta HTTP 400 si el UUID no es válido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred"); // Respuesta HTTP 500 si ocurre un error inesperado
        }
    }

    // Método GET para obtener todas las imágenes almacenadas
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/getImagesByArticuloId/{id}")
    public ResponseEntity<?> getAll(@PathVariable Long id) {
        try {
            return facade.getAllImagesByArticuloId(id); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @DeleteMapping("/baja/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        facade.deleteById(id);
        return ResponseEntity.ok(null);
    }


}
