package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{
    List<Categoria> findByEsInsumoTrue();
    List<Categoria> findByEsInsumoFalse();
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.sucursales WHERE c.id = :id")
    Categoria findWithSucursalesById(@Param("id") Long id);
    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false AND c.esInsumo=true")
    List<Categoria> findCategoriasInsumoBySucursalId(@Param("idSucursal") Long idSucursal);
    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false AND c.esInsumo=false")
    List<Categoria> findCategoriasManufacturadoBySucursalId(@Param("idSucursal") Long idSucursal);
    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND c.eliminado=false")
    List<Categoria> findAllCategoriasBySucursalId(@Param("idSucursal") Long idSucursal);
}
