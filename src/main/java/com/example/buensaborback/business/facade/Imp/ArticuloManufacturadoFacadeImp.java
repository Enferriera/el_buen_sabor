package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.ArticuloManufacturadoFacade;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.mapper.ArticuloManufacturadoMapper;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.ArticuloManufacturadoService;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoCreateDto;
import com.example.buensaborback.domain.dto.articulomanufacturadodto.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ArticuloManufacturadoFacadeImp extends BaseFacadeImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto, Long> implements ArticuloManufacturadoFacade {
    public ArticuloManufacturadoFacadeImp(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoDto> baseMapper) {
        super(baseService, baseMapper);

    }

    @Autowired
    private ArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    private ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Override
    @Transactional
    public ArticuloManufacturadoDto create(ArticuloManufacturadoCreateDto articuloManufacturadoCreateDto) {
        Optional<ArticuloManufacturado> existingArticulo = articuloManufacturadoService.findByCodigo(articuloManufacturadoCreateDto.getCodigo(), articuloManufacturadoCreateDto.getIdCategoria());
        if(existingArticulo.isPresent()){
            throw new RuntimeException("Articulo manufacturado con el codigo "+articuloManufacturadoCreateDto.getCodigo()+ " ya existe.");
        }
        var articulo = articuloManufacturadoMapper.toCreateEntity(articuloManufacturadoCreateDto);
        System.out.println("se mapeo el articulo");
        ArticuloManufacturado articuloPersisted = articuloManufacturadoService.create(articulo);
        return articuloManufacturadoMapper.toDTO(articuloPersisted);
    }

    @Override
    public void changeHabilitado(Long id) {
        articuloManufacturadoService.changeHabilitado(id);
    }

    @Override
    public List<ArticuloManufacturadoDto> getHabilitados(Long idSucursal) {
        return articuloManufacturadoMapper.toDTOsList(articuloManufacturadoService.getHabilitados(idSucursal));
    }

    @Override
    public List<ArticuloManufacturadoDto> findArticulosManufacturadosBySucursalId(Long id){
        return articuloManufacturadoMapper.toDTOsList(articuloManufacturadoService.findArticulosManufacturadosBySucursalId(id));
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImagesByArticuloId(Long id) {
        return articuloManufacturadoService.getAllImagesByArticuloId(id);
    }

    @Override
    public ResponseEntity<String> uploadImages(MultipartFile[] files, Long id) {
        return articuloManufacturadoService.uploadImages(files,id);
    }

    @Override
    public ResponseEntity<String> deleteImage(String publicId, Long id) {
        return articuloManufacturadoService.deleteImage(publicId, id);
    }
}
