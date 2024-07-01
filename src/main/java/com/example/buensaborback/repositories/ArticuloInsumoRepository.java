package com.example.buensaborback.repositories;


import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {


    @Query("SELECT ai FROM ArticuloInsumo ai JOIN ai.stocksInsumo st JOIN st.sucursal s JOIN s.empresa e WHERE e.id=:idEmpresa AND ai.esParaElaborar=true AND st.eliminado=false")
    List<ArticuloInsumo> findByEsParaElaborarTrue(@Param("idEmpresa") Long idEmpresa);

    @Query("SELECT ai FROM ArticuloInsumo ai JOIN ai.stocksInsumo st JOIN st.sucursal s JOIN s.empresa e WHERE e.id=:idEmpresa AND ai.esParaElaborar=false AND st.eliminado=false")
    List<ArticuloInsumo> findByEsParaElaborarFalse(@Param("idEmpresa") Long idEmpresa);

    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_INSUMO ai\n" +
            "JOIN ARTICULO a ON ai.ID = a.ID\n" +
            "WHERE a.CATEGORIA_ID = ?1", nativeQuery = true)
    List<ArticuloInsumo> getArticulosByCategoria(Long idCategoria);


    @Query("SELECT ai FROM ArticuloInsumo ai WHERE ai.id = :id AND ai.eliminado=false AND ai.codigo = :codigo")
    Optional<ArticuloInsumo> findByCodigoAndId(@Param("codigo") String codigo,@Param("id") Long id);


    @Query("SELECT ai FROM ArticuloInsumo ai JOIN ai.categoria c JOIN c.sucursales s JOIN s.empresa WHERE ai.codigo = :codigo AND ai.eliminado=false AND c.id = :idCategoria")
    Optional<ArticuloInsumo> findByCodigo(String codigo,@Param("idCategoria") Long idCategoria);

    @Query("SELECT ai FROM ArticuloInsumo ai JOIN ai.categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND ai.eliminado=false")
    List<ArticuloInsumo> findArticulosInsumosBySucursalId(@Param("idSucursal") Long idSucursal);
}
