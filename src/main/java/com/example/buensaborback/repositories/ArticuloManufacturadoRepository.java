package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {
    @Query(value = "SELECT EXISTS(\n" +
            "    SELECT 1\n" +
            "    FROM PROMOCION_DETALLE pd\n" +
            "    WHERE pd.ARTICULO_ID = ?1 AND ELIMINADO = FALSE\n" +
            ");", nativeQuery = true)
    boolean contiene(Long id);

    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_MANUFACTURADO am\n" +
            "JOIN ARTICULO a ON am.ID = a.ID\n" +
            "WHERE a.CATEGORIA_ID = ?1", nativeQuery = true)
    List<ArticuloManufacturado> getArticulosByCategoria(Long id);


    @Query("SELECT am FROM ArticuloManufacturado am JOIN am.categoria c JOIN c.sucursales s WHERE s.id=:idSucursal AND am.habilitado=true AND am.eliminado=false")
    List<ArticuloManufacturado> getHabilitados(@Param("idSucursal") Long idSucursal);

    @Query("SELECT am FROM ArticuloManufacturado am JOIN am.categoria c JOIN c.sucursales s JOIN s.empresa WHERE am.codigo = :codigo AND am.eliminado=false AND c.id = :idCategoria")
    Optional<ArticuloManufacturado> findByCodigo(@Param("codigo") String codigo,@Param("idCategoria") Long idCategoria);

    @Query("SELECT am FROM ArticuloManufacturado am WHERE am.id = :id AND am.eliminado=false AND am.codigo = :codigo")
    Optional<ArticuloManufacturado> findByCodigoAndId(@Param("codigo") String codigo,@Param("id") Long id);

    @Query("SELECT am FROM ArticuloManufacturado am JOIN am.categoria c JOIN c.sucursales s WHERE s.id = :idSucursal AND am.eliminado=false")
    List<ArticuloManufacturado> findArticulosManufacturadosBySucursalId(@Param("idSucursal") Long idSucursal);


}
