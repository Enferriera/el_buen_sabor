package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo,Long> {
    List<ArticuloInsumo> findByEsParaElaborarTrue(Long idEmpresa);

    List<ArticuloInsumo> findByEsParaElaborarFalse(Long idEmpresa);

    public void changeHabilitado(Long id);

    @Override
    public void deleteById(Long id);



    public Optional<ArticuloInsumo> findByCodigo(String codigo,Long idCategoria);

    List<ArticuloInsumo> findArticulosInsumosBySucursalId(Long idSucursal);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);

}
