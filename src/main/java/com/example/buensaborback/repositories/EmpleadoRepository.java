package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, Long>{

    Empleado findByEmail(String email);
    int countByRol(Rol rol);
    @Query("SELECT e FROM Empleado e JOIN e.sucursal s WHERE s.id=:idSucursal AND e.eliminado=FALSE ")
    List<Empleado> findAllBySucursalId(@Param("idSucursal")Long idSucursal);
}
