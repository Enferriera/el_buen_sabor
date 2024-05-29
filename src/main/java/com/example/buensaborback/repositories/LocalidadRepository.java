package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Localidad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad,Long>{
    List<Localidad> findByProvinciaId(Long id);
}
