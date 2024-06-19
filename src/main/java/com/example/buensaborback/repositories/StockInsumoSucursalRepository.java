package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StockInsumoSucursalRepository extends BaseRepository<StockInsumoSucursal,Long>{

    @Query("SELECT s FROM StockInsumoSucursal s WHERE s.sucursal.id = :idSucursal AND s.eliminado = false")
    Set<StockInsumoSucursal> findAllBySucursalId(@Param("idSucursal") Long idSucursal);

}
