package com.example.buensaborback.business.facade.Base;


import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseFacadeImp<E extends Base,D extends BaseDto, GD extends BaseDto, ID extends Serializable> implements BaseFacade<D,GD,ID > {

    protected BaseService<E,ID> baseService;
    protected BaseMapper<E,D,GD> baseMapper;

    public BaseFacadeImp(BaseService<E,ID> baseService, BaseMapper<E,D,GD> baseMapper) {
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    public GD createNew(D request){
        // Convierte a entidad
        var entityToCreate = baseMapper.toEntity(request);
        // Graba una entidad
        var entityCreated = baseService.create(entityToCreate);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityCreated);
    }

    public GD getById(ID id){
        // Busca una entidad por id
        var entity = baseService.getById(id);
        // convierte la entidad a DTO
        return baseMapper.toDTO(entity);
    }

    public List<GD> getAll(){
        // trae una lista de entidades
        var entities = baseService.getAll();
        //  devuelve una lista de DTO
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<GD> getAllPaged(Pageable pageable){
        Page<E> entities = baseService.getAllPaged(pageable);
        // Mapea las entidades a DTOs
        List<GD> dtos = entities.getContent().stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    public List<GD> getAllByBajaFalse(){
        // trae una lista de entidades
        var entities = baseService.getAllByBajaFalse();
        //  devuelve una lista de DTO
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Page<GD> getAllPagedByBajaFalse(Pageable pageable){
        // Trae una página de entidades
        Page<E> entities = baseService.getAllPagedByBajaFalse(pageable);
        // Mapea las entidades a DTOs
        List<GD> dtos = entities.getContent().stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
        // Devuelve una página de DTOs
        return new PageImpl<>(dtos, pageable, entities.getTotalElements());
    }

    public void deleteById(ID id){
        var entity = baseService.getById(id);
        baseService.deleteById(id);
    }

    public GD update(D request, ID id){
        var entityToUpdate = baseMapper.toEntity(request);
        var entityUpdated = baseService.update(entityToUpdate, id);
        return baseMapper.toDTO(entityUpdated);
    }

}
