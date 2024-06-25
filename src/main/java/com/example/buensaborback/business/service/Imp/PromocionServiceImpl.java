package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.CloudinaryService;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.ImagenPromocionRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class PromocionServiceImpl extends BaseServiceImp<Promocion,Long> implements PromocionService {
    private final PromocionRepository promocionRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private ImagenPromocionRepository imagenPromocionRepository;
    @Autowired
    private SucursalRepository sucursalRepository;

    public PromocionServiceImpl(PromocionRepository promocionRepository) {
        super();
        this.promocionRepository = promocionRepository;
    }

    @Override
    @Transactional
    public void changeHabilitado(Long id) {
        var promocion = getById(id);
        promocion.setHabilitado(!promocion.isHabilitado());
        baseRepository.save(promocion);
    }

    @Transactional
    public List<Promocion> getHabilitados(Long idSucursal) {
        return promocionRepository.getHabilitados(idSucursal);
    }

    @Override
    @Transactional
    public List<Promocion> findPromocionesBySucursalId(Long idSucursal){
        return promocionRepository.findPromocionesBySucursalId(idSucursal);
    }

    // Método para obtener todas las imágenes almacenadas
    @Override
    //pedimos el id para retornar solo las imagenes de un articulo
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByPromocionId(Long id) {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenPromocion> images = baseRepository.getById(id).getImagenes().stream().toList();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenPromocion image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());
                imageList.add(imageMap);
            }

            // Devolver la lista de imágenes como ResponseEntity con estado OK (200)
            return ResponseEntity.ok(imageList);
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error interno del servidor (500) si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para subir imágenes a Cloudinary y guardar los detalles en la base de datos
    @Override
    //Pedimos el id de articulo para saber a que articulo asignar las imagenes
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long idPromocion) {
        List<String> urls = new ArrayList<>();
        var promocion = baseRepository.getById(idPromocion);
        //por medio de un condicional limitamos la carga de imagenes a un maximo de 3 por aticulo
        //en caso de tratar de excer ese limite arroja un codigo 413 con el mensaje La cantidad maxima de imagenes es 3
        if(promocion.getImagenes().size() >= 3)
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("La cantidad maxima de imagenes es 3");
        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }

                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenPromocion image = new ImagenPromocion();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

                // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
                if (image.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }

                //Se asignan las imagenes al insumo
                promocion.getImagenes().add(image);
                //Se guarda la imagen en la base de datos
                imagenPromocionRepository.save(image);
                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            //se actualiza el insumo en la base de datos con las imagenes
            baseRepository.save(promocion);

            // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
            return new ResponseEntity<>("{\"status\":\"OK\", \"urls\":" + urls + "}", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    // Método para eliminar una imagen por su identificador en la base de datos y en Cloudinary
    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        try {
            // Eliminar la imagen de la base de datos usando su identificador
            imagenPromocionRepository.deleteById(id);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId, id);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deletePromocionInSucursales(Long idPromocion, Long idSucursal){
        Promocion promocionExistente = promocionRepository.getById(idPromocion);


        Sucursal sucursal = sucursalService.getById(idSucursal);

        // Eliminar la relación entre la sucursal y la categoría existente
        sucursal.getPromociones().remove(promocionExistente);
        promocionExistente.getSucursales().remove(sucursal);

        promocionRepository.save(promocionExistente);
    }

    @Override
    public Promocion mapIdToPromocion(Long idPromocion){
        if (idPromocion == null) {
            return null;
        }
        return promocionRepository.getById(idPromocion);
    }

    @Override
    @Transactional
    public Promocion create(Promocion promocion){
        promocion=promocionRepository.save(promocion);
        for(Sucursal sucursal:promocion.getSucursales()){
            sucursal=sucursalRepository.getById(sucursal.getId());
            sucursal.getPromociones().add(promocion);
            sucursalRepository.save(sucursal);
        }

        return promocion;
    }
}
