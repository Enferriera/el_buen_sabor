package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.Sucursal;
import org.mapstruct.Named;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PromocionService extends BaseService<Promocion,Long> {
    public void changeHabilitado(Long id);
    public List<Promocion> getHabilitados(Long sucursalId);
    @Override
    public Promocion create(Promocion promocion);

    List<Promocion> findPromocionesBySucursalId(Long idSucursal);
    void deletePromocionInSucursales(Long idPromocion, Long idSucursal);
    @Named("mapIdToPromocion")
    public Promocion mapIdToPromocion(Long idPromocion);
    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByPromocionId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);


}
