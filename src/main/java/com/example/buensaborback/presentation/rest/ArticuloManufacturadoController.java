package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/articulosManufacturados")
@CrossOrigin(origins="*")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto,Long, ArticuloManufacturadoFacadeImp> {

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @PostMapping("/create")
    public ResponseEntity<ArticuloManufacturadoDto> create(@RequestBody ArticuloManufacturadoCreateDto dto) {
        return ResponseEntity.ok().body(facade.create(dto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @PutMapping("/changeHabilitado/{id}")
    public ResponseEntity<?> changeHabilitado(@PathVariable Long id){
        facade.changeHabilitado(id);
        return ResponseEntity.ok().body("Se cambio el estadoPedido del Articulo Manufacturado");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/getHabilitados/{idSucursal}")
    public ResponseEntity<?> getHabilitados(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.getHabilitados(idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE','COCINERO')")
    @GetMapping("/getManufacturadosPorSucursal/{idSucursal}")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getManufacturadosPorSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.findArticulosManufacturadosBySucursalId(idSucursal));
    }

    // Método POST para subir imágenes
   // @PreAuthorize("hasAnyAuthority('COCINERO','ADMIN')")
    @PreAuthorize("hasAnyAuthority('ADMIN','COCINERO','GERENTE')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN','COCINERO')")
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
    @GetMapping("/getImagesByArticuloId/{id}")
    public ResponseEntity<?> getAll(@PathVariable Long id) {
        try {
            return facade.getAllImagesByArticuloId(id); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }
}
