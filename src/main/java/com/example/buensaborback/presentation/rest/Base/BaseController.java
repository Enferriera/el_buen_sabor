package com.example.buensaborback.presentation.rest.Base;

import com.example.buensaborback.domain.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseController <D extends BaseDto, GD, ID extends Serializable> {
    ResponseEntity<GD> getById(ID id);

    ResponseEntity<List<GD>> getAll();
    ResponseEntity<Page<GD>> getAllPaged(Pageable pageable);

    ResponseEntity<GD> create(D entity);

    ResponseEntity<GD> edit(D entity, ID id);

    ResponseEntity<?> deleteById(ID id);
}