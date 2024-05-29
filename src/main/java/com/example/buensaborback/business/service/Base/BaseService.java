package com.example.buensaborback.business.service.Base;

import com.example.buensaborback.domain.entities.Base;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends Base, ID extends Serializable>{
    public E create(E request);
    @Named("getById")
    public E getById(ID id);
    public List<E> getAll();
    public Page<E> getAllPaged(Pageable pageable);
    public List<E> getAllByBajaFalse();
    public Page<E> getAllPagedByBajaFalse(Pageable pageable);
    public void deleteById(ID id);
    public E update(E request, ID id);
}
