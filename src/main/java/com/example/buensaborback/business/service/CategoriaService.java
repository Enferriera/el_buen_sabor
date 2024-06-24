package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria,Long> {
    List<Categoria> findByEsInsumoTrue();
    List<Categoria> findByEsInsumoFalse();

    void deleteCategoriaInSucursales(Long idCategoria, Long idSucursal);
    List<Categoria> findCategoriasInsumoBySucursalId(Long idSucursal);
    List<Categoria> findCategoriasManufacturadoBySucursalId(Long idSucursal);

    List<Categoria> findAllCategoriasBySucursalId(Long idSucursal);

    @Override
    void deleteById(Long aLong);
}
