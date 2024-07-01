package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.ArticuloInsumoService;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.CloudinaryService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ArticuloImsumoServiceImp extends BaseServiceImp<ArticuloInsumo,Long> implements ArticuloInsumoService {
    @Autowired
    ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    ImagenArticuloRepository imagenArticuloRepository;

    @Autowired
    ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    StockInsumoSucursalRepository stockInsumoSucursalRepository;

    @Autowired
    private CloudinaryService   cloudinaryService;

    @Override
    @Transactional
    public ArticuloInsumo create(ArticuloInsumo request) {
        Set<ImagenArticulo> imagenes = request.getImagenes();
        Set<ImagenArticulo> imagenesPersistidas = new HashSet<>();

        if (!imagenes.isEmpty()) {
            System.out.println("Entro al if");
            for (ImagenArticulo imagen : imagenes) {
                System.out.println("imagen: " + imagen);
                if (imagen.getId() != null) {
                    Optional<ImagenArticulo> imagenBd = imagenArticuloRepository.findById(imagen.getId());
                    imagenBd.ifPresent(imagenesPersistidas::add);
                } else {
                    System.out.println("no tiene id: " + imagen);
                    imagen.setEliminado(false);
                    ImagenArticulo savedImagen = imagenArticuloRepository.save(imagen);
                    imagenesPersistidas.add(savedImagen);
                }
            }
            request.setImagenes(imagenesPersistidas);
        }

        if (request.getCategoria() != null) {
            Categoria categoria = categoriaRepository.getById(request.getCategoria().getId());
            if (categoria == null) {
                throw new RuntimeException("La categoría con id: " + request.getCategoria().getId() + " no existe");
            }


            request.setCategoria(categoria);
        }
        StockInsumoSucursal stockBase = request.getStocksInsumo().stream().findFirst().orElse(null);
        if (stockBase != null) {
            // Lista temporal para almacenar los nuevos stocks
            Set<StockInsumoSucursal> nuevosStocks = new HashSet<>();

            for (Sucursal sucursal : request.getCategoria().getSucursales()) {
                // Crear un nuevo stock basado en stockBase
                StockInsumoSucursal nuevoStock = StockInsumoSucursal.builder()
                        .stockActual(stockBase.getStockActual())
                        .stockMinimo(stockBase.getStockMinimo())
                        .stockMaximo(stockBase.getStockMaximo())
                        .sucursal(sucursal)
                        .articuloInsumo(request)
                        .build();

                // Añadir el nuevo stock a la lista temporal
                nuevosStocks.add(nuevoStock);
            }

            // Añadir todos los nuevos stocks al conjunto original
            request.setStocksInsumo(nuevosStocks);


        }
        return articuloInsumoRepository.save(request);
    }
    @Override
    public ArticuloInsumo update(ArticuloInsumo request, Long id) {
        Optional<ArticuloInsumo> articuloEditar  = articuloInsumoRepository.findByCodigoAndId(request.getCodigo(), id);
        if(articuloEditar.isEmpty()) {
            Optional<ArticuloInsumo> existingArticulo = articuloInsumoRepository.findByCodigo(request.getCodigo(),request.getCategoria().getId());
            if (existingArticulo.isPresent()) {
                throw new RuntimeException("Articulo manufacturado con el codigo " + request.getCodigo() + " ya existe.");
            }
        }
        ArticuloInsumo articulo = articuloInsumoRepository.getById(id);
        if (articulo == null) {
            throw new RuntimeException("Insumo no encontrado: { id: " + id + " }");
        }

        Set<ImagenArticulo> imagenes = request.getImagenes();
        Set<ImagenArticulo> imagenesPersistidas = new HashSet<>();

        if (imagenes != null && !imagenes.isEmpty()) {
            for (ImagenArticulo imagen : imagenes) {
                if (imagen.getId() != null) {
                    ImagenArticulo imagenBd = imagenArticuloRepository.getById(imagen.getId());
                    imagenesPersistidas.add(imagenBd);
                } else {
                    ImagenArticulo savedImagen = imagenArticuloRepository.save(imagen);
                    imagenesPersistidas.add(savedImagen);
                }
            }
            articulo.setImagenes(imagenesPersistidas);
        }

        if (!imagenesPersistidas.isEmpty()) {
            request.setImagenes(imagenesPersistidas);
        }

        if (request.getCategoria() != null) {
            Categoria categoria = categoriaRepository.getById(request.getCategoria().getId());
            if (categoria == null ) {
                throw new RuntimeException("La categoría con id: " + request.getCategoria().getId() + " no existe");
            }
            if (!categoria.isEsInsumo()) {
                throw new RuntimeException("La categoría con id: " + request.getCategoria().getId() + " no pertenece a una categoría de insumos");
            }

            request.setCategoria(categoria);
        }

        return articuloInsumoRepository.save(request);

    }

    @Override
    public void deleteById(Long id) {
        System.out.println("entre al delete correcto");
        ArticuloInsumo insumo = articuloInsumoRepository.getById(id);
        if (insumo == null) {
            throw new RuntimeException("Insumo no encontrado: { id: " + id + " }");
        }
        List<ArticuloManufacturadoDetalle> insumoEsUtilizado = articuloManufacturadoDetalleRepository.getByArticuloInsumo(insumo.getId());
        if (!insumoEsUtilizado.isEmpty()) {
            System.out.println("si tengo un manufacturado detalle");
            throw new RuntimeException("No se puede eliminar el articulo porque está presente en un detalle");
        }
        for(StockInsumoSucursal stock: insumo.getStocksInsumo()){
            StockInsumoSucursal stockBase=stockInsumoSucursalRepository.getById(stock.getId());
            stockInsumoSucursalRepository.delete(stockBase);
        }
        articuloInsumoRepository.delete(insumo);
    }

    @Override
    public List<ArticuloInsumo> findByEsParaElaborarTrue(Long idEmpresa) {
        return articuloInsumoRepository.findByEsParaElaborarTrue(idEmpresa);
    }

    @Override
    public List<ArticuloInsumo> findByEsParaElaborarFalse(Long idEmpresa) {
        return articuloInsumoRepository.findByEsParaElaborarFalse(idEmpresa);
    }


    @Override
    public void changeHabilitado(Long id) {
        var articulo = getById(id);
        articulo.setHabilitado(!articulo.isHabilitado());
        baseRepository.save(articulo);
    }

    @Override
    public Optional<ArticuloInsumo> findByCodigo(String codigo,Long idCategoria) {
        return articuloInsumoRepository.findByCodigo(codigo, idCategoria);
    }

    @Override
    @Transactional
    public List<ArticuloInsumo> findArticulosInsumosBySucursalId(Long idSucursal){
        return articuloInsumoRepository.findArticulosInsumosBySucursalId(idSucursal);
    }

    // Método para obtener todas las imágenes almacenadas
    @Override
    //pedimos el id para retornar solo las imagenes de un articulo
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id) {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenArticulo> images = baseRepository.getById(id).getImagenes().stream().toList();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenArticulo image : images) {
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
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long idArticulo) {
        List<String> urls = new ArrayList<>();
        var insumo = baseRepository.getById(idArticulo);
        //por medio de un condicional limitamos la carga de imagenes a un maximo de 3 por aticulo
        //en caso de tratar de excer ese limite arroja un codigo 413 con el mensaje La cantidad maxima de imagenes es 3
        if(insumo.getImagenes().size() >= 3)
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("La cantidad maxima de imagenes es 3");
        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().build();
                }

                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenArticulo image = new ImagenArticulo();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

                // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
                if (image.getUrl() == null) {
                    return ResponseEntity.badRequest().build();
                }

                //Se asignan las imagenes al insumo
                insumo.getImagenes().add(image);
                //Se guarda la imagen en la base de datos
                imagenArticuloRepository.save(image);
                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            //se actualiza el insumo en la base de datos con las imagenes
            baseRepository.save(insumo);

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
            imagenArticuloRepository.deleteById(id);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId, id);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }


}
