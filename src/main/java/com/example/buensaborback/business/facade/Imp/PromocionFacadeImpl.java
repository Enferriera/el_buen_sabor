package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PromocionFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PromocionMapper;
import com.example.buensaborback.business.mapper.SucursalMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.Imp.PromocionServiceImpl;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.domain.dto.promocionDto.PromocionCreateDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.entities.Promocion;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class PromocionFacadeImpl extends BaseFacadeImp<Promocion, PromocionDto, PromocionDto, Long> implements PromocionFacade {
    private final PromocionMapper promocionMapper;
    private final PromocionServiceImpl promocionServiceImpl;
    @Autowired
    private PromocionService promocionService;

    @Autowired
    private SucursalMapper sucursalMapper;

    public PromocionFacadeImpl(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionDto> baseMapper, PromocionMapper promocionMapper, PromocionServiceImpl promocionServiceImpl) {
        super(baseService, baseMapper);
        this.promocionMapper = promocionMapper;
        this.promocionServiceImpl = promocionServiceImpl;
    }

    @Override
    @Transactional
    public PromocionDto create(PromocionCreateDto promocionDto) {
        System.out.println("PromocionFacadeImpl: " + promocionDto.getDenominacion());
        Promocion promocion = promocionMapper.toCreateEntity(promocionDto);
        System.out.println("PromocionFacadeImpl: " + promocion.getDenominacion());
        return promocionMapper.toDTO(promocionService.create(promocion));
    }

    @Override
    public void changeHabilitado(Long id) {
        promocionServiceImpl.changeHabilitado(id);
    }

    @Override
    public List<PromocionDto> getHabilitados(Long idSucursal) {
        return promocionMapper.toDTOsList(promocionService.getHabilitados(idSucursal));
    }

    @Override
    public List<PromocionDto> findPromocionesBySucursalId(Long idSucursal) {
        return promocionMapper.toDTOsList(promocionService.findPromocionesBySucursalId(idSucursal));
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByPromocionId(Long id) {
        return promocionService.getAllImagesByPromocionId(id);
    }

    @Override
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long id) {
        return promocionService.uploadImages(files, id);
    }

    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        return promocionService.deleteImage(publicId, id);
    }

    @Override
    public void deletePromocionInSucursales(Long idPromocion, Long idSucursal) {
        promocionService.deletePromocionInSucursales(idPromocion, idSucursal);
    }
}

