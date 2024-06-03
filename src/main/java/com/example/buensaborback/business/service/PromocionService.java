package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;

import java.util.List;

public interface PromocionService extends BaseService<Promocion,Long> {
    public void changeHabilitado(Long id);
    public List<Promocion> getHabilitados();
}
