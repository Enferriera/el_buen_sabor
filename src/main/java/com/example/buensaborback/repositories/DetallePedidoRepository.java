package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.dto.Estadisticas.RankingProductos;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long>{

    @Query(value = "select am.denominacion as denominacion, count(am.id) as countVentas " +
            "from detalle_pedido dp " +
            "inner join articulo am on am.id = dp.articulo_id " +
            "inner join pedido p on dp.pedido_id = p.id " +
            "where CAST(p.fecha_pedido AS DATE) between :initialDate and :endDate " +
            "group by am.id, am.denominacion " +
            "order by countVentas desc",
            nativeQuery = true)
    List<RankingProductos> bestProducts(@Param("initialDate") Date initialDate, @Param("endDate") Date endDate);

}
