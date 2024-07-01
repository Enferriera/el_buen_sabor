package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado,Long> {

    public void changeHabilitado(Long id);


    public List<ArticuloManufacturado> getHabilitados(Long idSucursal);

    public Optional<ArticuloManufacturado> findByCodigo(String codigo, Long idCategoria);

   public List<ArticuloManufacturado> findArticulosManufacturadosBySucursalId(Long id);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y Long
    ResponseEntity<String> deleteImage(String publicId, Long id);

    @Override
    public ArticuloManufacturado update(ArticuloManufacturado request, Long id);
}
