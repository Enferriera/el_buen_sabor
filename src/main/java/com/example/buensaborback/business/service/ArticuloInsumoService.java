package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo,Long> {
    List<ArticuloInsumo> findByEsParaElaborarTrue();

    List<ArticuloInsumo> findByEsParaElaborarFalse();

    public void changeHabilitado(Long id);

    public Optional<ArticuloInsumo> findByCodigo(String codigo);

    List<ArticuloInsumo> findArticulosInsumosBySucursalId(Long idSucursal);
}
