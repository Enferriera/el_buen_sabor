package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_MANUFACTURADO am\n" +
            "JOIN ARTICULO a ON am.ID = a.ID\n" +
            "WHERE a.HABILITADO = TRUE", nativeQuery = true)
    List<ArticuloManufacturado> getHabilitados();
}
