package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoCreateDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.ArticuloInsumoShortDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ArticuloInsumoFacade extends BaseFacade<ArticuloInsumoDto, ArticuloInsumoDto, Long> {
    public void changeHabilitado(Long id);
    public List<ArticuloInsumoDto> findByEsParaElaborarTrue(Long idEmpresa);
    public List<ArticuloInsumoDto> findByEsParaElaborarFalse(Long idEmpresa);
    public ArticuloInsumoDto create(ArticuloInsumoCreateDto articuloInsumoCreateDto);
    @Override
    public void deleteById(Long id);
    public List<ArticuloInsumoDto> findArticulosInsumosBySucursalId(Long idSucursal);

    //Imagenes
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id);
    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files, Long id);
    // Método para eliminar una imagen por su identificador público y UUID
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
