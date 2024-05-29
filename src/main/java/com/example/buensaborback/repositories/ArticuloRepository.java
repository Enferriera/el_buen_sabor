package com.example.buensaborback.repositories;


import com.example.buensaborback.domain.entities.Articulo;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo, Long> {
}
