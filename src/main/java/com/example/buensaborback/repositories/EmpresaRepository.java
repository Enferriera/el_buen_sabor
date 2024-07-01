package com.example.buensaborback.repositories;


import com.example.buensaborback.domain.entities.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends BaseRepository<Empresa,Long> {
    @Query("SELECT e FROM Empresa e LEFT JOIN FETCH e.sucursales s WHERE e.id = :id AND e.eliminado=false AND s.eliminado=false")
    Empresa findWithSucursalesById(@Param("id") Long id);

}
