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
    Page<Categoria> findByEsInsumoTrue(Pageable pageable);
    Page<Categoria> findByEsInsumoFalse(Pageable pageable);
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.sucursales WHERE c.id = :id")
    Categoria findWithSucursalesById(@Param("id") Long id);
    @Query(value = "SELECT c.ID, c.ELIMINADO, c.DENOMINACION, c.CATEGORIA_ID\n" +
            "FROM CATEGORIA c\n" +
            "JOIN SUCURSAL_CATEGORIA sc ON c.ID = sc.CATEGORIA_ID\n" +
            "JOIN SUCURSAL s ON sc.SUCURSAL_ID = s.ID\n" +
            "WHERE s.ID = ?1", nativeQuery = true)
    List<Categoria> getCategoriasBySucursal(Long idSucursal);
}
