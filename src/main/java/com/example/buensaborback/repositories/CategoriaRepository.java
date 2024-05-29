package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{
    Page<Categoria> findByEsInsumoTrue(Pageable pageable);
    Page<Categoria> findByEsInsumoFalse(Pageable pageable);
}
