package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface StockInsumoSucursalService extends BaseService<StockInsumoSucursal,Long> {

    Set<StockInsumoSucursal> findAllBySucursalId( Long idSucursal);


}
