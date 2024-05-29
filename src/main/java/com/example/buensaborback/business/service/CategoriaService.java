package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService extends BaseService<Categoria,Long> {
    Page<Categoria> findByEsInsumoTrue(Pageable pageable);
    Page<Categoria> findByEsInsumoFalse(Pageable pageable);

    void deleteInSucursales(Long id, SucursalShortDto sucursal);
}
