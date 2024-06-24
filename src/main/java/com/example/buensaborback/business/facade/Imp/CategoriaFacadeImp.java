package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.CategoriaFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.CategoriaMapper;
import com.example.buensaborback.business.mapper.SucursalMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.CategoriaService;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaFacadeImp extends BaseFacadeImp<Categoria, CategoriaGetDto, CategoriaGetDto,  Long> implements CategoriaFacade {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    SucursalMapper sucursalMapper;

    public CategoriaFacadeImp(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaGetDto, CategoriaGetDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Transactional
    public List<CategoriaGetDto> getCategoriaInsumos () {
        // Trae una página de entidades
        List<Categoria> entities = categoriaService.findByEsInsumoTrue();
        // Mapea las entidades a DTOs
        List<CategoriaGetDto> dtos = entities.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return dtos;
    }

    @Transactional
    public List<CategoriaGetDto> getCategoriaManufacturados () {
        // Trae una página de entidades
        List<Categoria> entities = categoriaService.findByEsInsumoFalse();
        // Mapea las entidades a DTOs
        List<CategoriaGetDto> dtos = entities.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return dtos;
    }

    @Transactional
    public void deleteCategoriaInSucursales (Long idCategoria, Long idSucursal) {
        categoriaService.deleteCategoriaInSucursales(idCategoria, idSucursal);
    }

    @Transactional
    public CategoriaGetDto createNew(CategoriaPostDto dto) {
        Categoria categoria = categoriaMapper.toEntityCreate(dto);
        System.out.println("categoria: " + categoria.getId());
        return categoriaMapper.toDTO(categoriaService.create(categoria));
    }

    @Override
    @Transactional
    public List<CategoriaDto> findCategoriasInsumoBySucursalId(Long idSucursal){
        return categoriaMapper.toDTOList(categoriaService.findCategoriasInsumoBySucursalId(idSucursal));
    }

    @Override
    @Transactional
    public List<CategoriaDto> findCategoriasManufacturadoBySucursalId(Long idSucursal){
        return categoriaMapper.toDTOList(categoriaService.findCategoriasManufacturadoBySucursalId(idSucursal));
    }

    @Override
    @Transactional
    public List<CategoriaGetDto> findAllCategoriasBySucursalId(Long idSucursal){
        return categoriaMapper.toDTOsList(categoriaService.findAllCategoriasBySucursalId(idSucursal));
    }

}
