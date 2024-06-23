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
            "and p.sucursal_id = :idSucursal and p.estado_pedido='COMPLETADO' " +
            "group by am.id, am.denominacion " +
            "order by countVentas desc",
            nativeQuery = true)
    List<RankingProductos> bestProducts(@Param("initialDate") Date initialDate,
                                        @Param("endDate") Date endDate,
                                        @Param("idSucursal") Long idSucursal);

    @Query("SELECT d FROM Pedido p JOIN p.detallePedidos d WHERE p.id = :idPedido")
    List<DetallePedido> findAllByPedidoId(@Param("idPedido")Long idPedido);


    @Query(value = "SELECT s.nombre AS sucursal, am.denominacion AS denominacion, COUNT(am.id) AS countVentas " +
            "FROM detalle_pedido dp " +
            "INNER JOIN articulo am ON am.id = dp.articulo_id " +
            "INNER JOIN pedido p ON dp.pedido_id = p.id " +
            "INNER JOIN sucursal s ON p.sucursal_id = s.id " +
            "INNER JOIN empresa e ON s.empresa_id = e.id " +
            "WHERE CAST(p.fecha_pedido AS DATE) BETWEEN :initialDate AND :endDate " +
            "AND e.id = :idEmpresa " +
            "GROUP BY s.nombre, am.id, am.denominacion " +
            "ORDER BY countVentas DESC",
            nativeQuery = true)
    List<RankingProductos> bestProductsByEmpresa(@Param("initialDate") Date initialDate,
                                                 @Param("endDate") Date endDate,
                                                 @Param("idEmpresa") Long idEmpresa);



}
