package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.dto.Estadisticas.RankingProductos;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticuloManufacturadoDetalleRepository extends BaseRepository<ArticuloManufacturadoDetalle,Long> {
    @Query("SELECT amd FROM ArticuloManufacturadoDetalle amd WHERE  amd.articuloInsumo.id=:idInsumo AND amd.eliminado=false")
    List<ArticuloManufacturadoDetalle> getByArticuloInsumo(@Param("idInsumo") Long idInsumo);


}
