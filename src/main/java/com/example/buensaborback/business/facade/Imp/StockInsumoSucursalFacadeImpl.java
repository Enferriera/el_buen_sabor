package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.StockInsumoSucursalFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.StockInsumoSucursalMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.StockInsumoSucursalService;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockCreateSucursalDto;
import com.example.buensaborback.domain.dto.articuloInsumoDto.StockInsumoShortDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StockInsumoSucursalFacadeImpl extends BaseFacadeImp<StockInsumoSucursal, StockInsumoShortDto, StockInsumoShortDto, Long> implements StockInsumoSucursalFacade {
    public StockInsumoSucursalFacadeImpl(BaseService<StockInsumoSucursal, Long> baseService, BaseMapper<StockInsumoSucursal, StockInsumoShortDto, StockInsumoShortDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    private StockInsumoSucursalService stockInsumoSucursalService;

    @Autowired
    private StockInsumoSucursalMapper stockInsumoSucursalMapper;

    @Override
    @Transactional
    public Set<StockInsumoShortDto> findAllBySucursalId(Long idSucursal){
        return stockInsumoSucursalMapper.toDTOsSet(stockInsumoSucursalService.findAllBySucursalId(idSucursal));
    }

    @Transactional
    public StockInsumoShortDto createNew(StockCreateSucursalDto source){
        var stockPersist= stockInsumoSucursalMapper.toCreateEntity(source);
        return stockInsumoSucursalMapper.toDTO(stockInsumoSucursalService.create(stockPersist));
    }

}
