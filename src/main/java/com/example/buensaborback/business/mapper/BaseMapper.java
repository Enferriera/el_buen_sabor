package com.example.buensaborback.business.mapper;

import java.util.List;
import java.util.Set;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;

public interface BaseMapper<E extends Base,D extends BaseDto, GetDto extends BaseDto>{
    public GetDto toDTO(E source);
    public E toEntity(D source);
    public List<D> toDTOsList(List<E> source);
    public Set<D> toDTOsSet(Set<E> source);


}
