package com.example.buensaborback.business.facade.Base;

import com.example.buensaborback.domain.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseFacade <D extends BaseDto, GD extends BaseDto, ID extends Serializable>{
    public GD createNew(D request);
    public GD getById(Long id);
    public List<GD> getAll();
    public Page<GD> getAllPaged(Pageable pageable);
    public void deleteById(Long id);
    public GD update(D request, Long id);
}
