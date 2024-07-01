package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario ,Long> {

    @Query("SELECT e FROM Empleado e JOIN e.usuario u WHERE u.auth0Id=:auth0Id AND u.eliminado=false")
    Empleado findByAuth0Id(@Param("auth0Id")String auth0Id);
}
