package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.StockInsumoSucursalService;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import com.example.buensaborback.repositories.StockInsumoSucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StockInsumoSucursalServiceImpl extends BaseServiceImp<StockInsumoSucursal,Long> implements StockInsumoSucursalService {

    @Autowired
    private StockInsumoSucursalRepository stockInsumoSucursalRepository;
    @Override
    @Transactional
   public Set<StockInsumoSucursal> findAllBySucursalId(Long idSucursal){
        return stockInsumoSucursalRepository.findAllBySucursalId(idSucursal);
    }


}
