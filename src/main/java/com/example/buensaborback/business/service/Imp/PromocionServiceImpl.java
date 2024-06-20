package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.repositories.PromocionRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionServiceImpl extends BaseServiceImp<Promocion,Long> implements PromocionService {
    private final PromocionRepository promocionRepository;

    public PromocionServiceImpl(PromocionRepository promocionRepository) {
        super();
        this.promocionRepository = promocionRepository;
    }

    @Override
    @Transactional
    public void changeHabilitado(Long id) {
        var promocion = getById(id);
        promocion.setHabilitado(!promocion.isHabilitado());
        baseRepository.save(promocion);
    }

    @Transactional
    public List<Promocion> getHabilitados() {
        return promocionRepository.getHabilitados();
    }

    @Override
    @Transactional
    public List<Promocion> findPromocionesBySucursalId(Long idSucursal){
        return promocionRepository.findPromocionesBySucursalId(idSucursal);
    }
}
