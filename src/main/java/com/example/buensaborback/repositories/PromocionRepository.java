package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocionRepository extends BaseRepository<Promocion,Long>{
    @Query("SELECT p FROM Promocion p LEFT JOIN FETCH p.sucursales WHERE p.id = :id")
    Promocion findAllWithSucursales(@Param("id") Long id);

    @Query("SELECT p FROM Promocion p JOIN p.sucursales s WHERE s.id=:sucursalId AND s.eliminado=false AND p.habilitado=true ")
    List<Promocion> getHabilitados(@Param("sucursalId") Long sucursalId);
    @Query("SELECT p FROM Promocion p JOIN p.sucursales s WHERE s.id = :idSucursal AND p.eliminado=false")
    List<Promocion> findPromocionesBySucursalId(@Param("idSucursal") Long idSucursal);
}
