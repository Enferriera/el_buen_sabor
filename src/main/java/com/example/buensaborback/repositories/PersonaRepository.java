package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
}
