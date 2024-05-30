package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloInsumoService extends BaseService<ArticuloInsumo,Long> {
    List<ArticuloInsumo> findByEsParaElaborarTrue();

    List<ArticuloInsumo> findByEsParaElaborarFalse();

    public void changeHabilitado(Long id);
}
