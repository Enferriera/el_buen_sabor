package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.Imp.PromocionFacadeImpl;

import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;

import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // Método POST para subir imágenes
  //  @PreAuthorize("hasAnyAuthority('EMPLEADO','ADMIN')")
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files,
            @RequestParam(value = "id", required = true) Long idPromocion) {
        try {
            return facade.uploadImages(files, idPromocion); // Llama al método del servicio para subir imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    // Método POST para eliminar imágenes por su publicId y Long
   // @PreAuthorize("hasAnyAuthority('EMPLEADO','ADMIN')")
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
            return facade.getAllImagesByPromocionId(id); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

}
