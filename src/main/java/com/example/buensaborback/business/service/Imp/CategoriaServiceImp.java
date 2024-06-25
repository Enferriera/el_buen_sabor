package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.mapper.SucursalMapper;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria,Long> implements CategoriaService {

    @Autowired
    SucursalMapper mapper;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SucursalServiceImpl sucursalService;

    @Override
    public List<Categoria> findByEsInsumoTrue() {
        return categoriaRepository.findByEsInsumoTrue();
    }

    @Override
    public List<Categoria> findByEsInsumoFalse() {
        return categoriaRepository.findByEsInsumoFalse();
    }


    @Override
    @Transactional
    public Categoria create(Categoria categoria) {
        Set<Sucursal> sucursales = new HashSet<>();

        // Verificar y asociar sucursales existentes
       if (categoria.getSucursales() != null && !categoria.getSucursales().isEmpty()) {
            for (Sucursal sucursal : categoria.getSucursales()) {
                Sucursal sucursalBd = sucursalService.getById(sucursal.getId());
                if (sucursalBd == null) {
                    throw new RuntimeException("La sucursal con el id " + sucursal.getId() + " no existe.");
                }
                sucursalBd.getCategorias().add(categoria);
                sucursales.add(sucursalBd);
            }
        }

        // Establecer la nueva colección de sucursales en la categoría
        categoria.setSucursales(sucursales);

        // Mapear subcategorías y guardar la categoría

        if(categoria.getCategoriaPadre()!= null){
            Categoria categoriaPadre = categoriaRepository.getById(categoria.getCategoriaPadre().getId());

            categoria.setCategoriaPadre(categoriaPadre);
            var categoriaHija=categoriaRepository.save(categoria);
            categoriaPadre.getSubCategorias().add(categoria);
            categoriaRepository.save(categoriaPadre);
            return categoriaHija;
        }
        return categoriaRepository.save(categoria);
    }

    private void mapearSubcategorias(Categoria categoria, Set<Sucursal> sucursales){
        if (!categoria.getSubCategorias().isEmpty()){
            for(Categoria subcategoria: categoria.getSubCategorias()){
                subcategoria.setEsInsumo(categoria.isEsInsumo());
                subcategoria.setCategoriaPadre(categoria);
                subcategoria.setSucursales(sucursales);
                for(Sucursal sucursal: sucursales)
                    sucursal.getCategorias().add(subcategoria);
                mapearSubcategorias(subcategoria, sucursales);
            }
        }
    }

    @Override
    @Transactional
    public void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal) {
        Categoria categoriaExistente = categoriaRepository.getById(idCategoria);


        Sucursal sucursal = sucursalService.getById(idSucursal);

        // Eliminar la relación entre la sucursal y la categoría existente
        sucursal.getCategorias().remove(categoriaExistente);
        categoriaExistente.getSucursales().remove(sucursal);

        categoriaRepository.save(categoriaExistente);
    }

    @Override
    @Transactional
    public Categoria update(Categoria newCategoria, Long id) {
        Categoria categoriaExistente = categoriaRepository.getById(id);


        // Actualizar los detalles básicos de la categoría
        categoriaExistente.setDenominacion(newCategoria.getDenominacion());
        categoriaExistente.setEsInsumo(newCategoria.isEsInsumo());

        // Actualizar las sucursales asociadas
        Set<Sucursal> newSucursales = newCategoria.getSucursales().stream()
                .map(sucursal -> sucursalService.getById(sucursal.getId()))
                .collect(Collectors.toSet());

        Set<Sucursal> existingSucursales = categoriaExistente.getSucursales();

        // Remover asociaciones obsoletas
        existingSucursales.removeIf(sucursal -> {
            boolean remove = !newSucursales.contains(sucursal);
            if (remove) {
                sucursal.getCategorias().remove(categoriaExistente);
                sucursalService.update(sucursal, sucursal.getId());
            }
            return remove;
        });

        // Agregar nuevas asociaciones
        for (Sucursal sucursal : newSucursales) {
            if (!existingSucursales.contains(sucursal)) {
                existingSucursales.add(sucursal);
                sucursal.getCategorias().add(categoriaExistente);
                sucursalService.update(sucursal, sucursal.getId());
            }
        }

        // Actualizar la relación de sucursales de la categoría existente
        categoriaExistente.setSucursales(existingSucursales);

        // Manejar subcategorías
        actualizarSubcategorias(categoriaExistente, newCategoria, newSucursales);

        return categoriaRepository.save(categoriaExistente);
    }


    private void actualizarSubcategorias(Categoria categoriaExistente, Categoria newCategoria, Set<Sucursal> sucursales){
        if (!newCategoria.getSubCategorias().isEmpty()){
            for(Categoria subcategoriaNueva: newCategoria.getSubCategorias()){
                Optional<Categoria> subcategoriaExistenteOpt = categoriaExistente.getSubCategorias().stream()
                        .filter(sc -> sc.getId().equals(subcategoriaNueva.getId()))
                        .findFirst();

                if (subcategoriaExistenteOpt.isPresent()) {
                    Categoria subcategoriaExistente = subcategoriaExistenteOpt.get();
                    subcategoriaExistente.setDenominacion(subcategoriaNueva.getDenominacion());
                    subcategoriaExistente.setEsInsumo(subcategoriaNueva.isEsInsumo());
                    subcategoriaExistente.setSucursales(sucursales);
                    for (Sucursal sucursal : sucursales) {
                        boolean categoriaExists = sucursal.getCategorias().stream()
                                .anyMatch(cat -> cat.getId() != null && cat.getId().equals(subcategoriaExistente.getId()));

                        if (!categoriaExists) {
                            sucursal.getCategorias().add(subcategoriaExistente);
                        }
                    }
                    actualizarSubcategorias(subcategoriaExistente, subcategoriaNueva, sucursales);
                } else {
                    subcategoriaNueva.setCategoriaPadre(categoriaExistente);
                    subcategoriaNueva.setSucursales(sucursales);
                    categoriaExistente.getSubCategorias().add(subcategoriaNueva);

                    for (Sucursal sucursal : sucursales) {
                        sucursal.getCategorias().add(subcategoriaNueva);
                    }
                    actualizarSubcategorias(subcategoriaNueva, subcategoriaNueva, sucursales);

                }
            }
        }
    }

    @Override
    @Transactional
    public  List<Categoria> findCategoriasInsumoBySucursalId(Long idSucursal){
        return categoriaRepository.findCategoriasInsumoBySucursalId(idSucursal);
    }

    @Override
    @Transactional
    public  List<Categoria> findCategoriasManufacturadoBySucursalId(Long idSucursal){
        return categoriaRepository.findCategoriasManufacturadoBySucursalId(idSucursal);
    }

    @Override
    @Transactional
    public List<Categoria> findAllCategoriasBySucursalId(Long idSucursal){
        return categoriaRepository.findAllCategoriasBySucursalId(idSucursal);
    }

   @Override
    public void deleteById(Long idCategoria){
        Categoria categoria= categoriaRepository.getById(idCategoria);
        if(categoria.getSubCategorias().size()>0){
            throw new RuntimeException("No se puede borrar la categoria "+categoria.getDenominacion()+" porque tiene asignadas subcategorias");
        }
        if(categoria.getArticulos().size()>0){
            throw new RuntimeException("No se puede borrar la categoria "+categoria.getDenominacion()+" porque tiene Articulos insumos asociados");
        }

       for (Sucursal sucursal : categoria.getSucursales()) {
           sucursal=sucursalService.getById(sucursal.getId());
           sucursal.getCategorias().remove(categoria);
           sucursalService.update(sucursal,sucursal.getId());// Desasociar la sucursal de la categoría
       }
       categoriaRepository.deleteById(categoria.getId());
   }




}
