package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;


import java.util.List;
import java.util.Optional;

public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado,Long> {

    public void changeHabilitado(Long id);


    public List<ArticuloManufacturado> getHabilitados();

    public Optional<ArticuloManufacturado> findByCodigo(String codigo);

}
