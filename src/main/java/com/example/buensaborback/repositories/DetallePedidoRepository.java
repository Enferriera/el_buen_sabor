package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.dto.Estadisticas.RankingProductos;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long>{

    @Query(value = "select denominacion, sum(countVentas) as countVentas " +
            "from ( " +
            "  select am.denominacion as denominacion, count(dp.id) as countVentas " +
            "  from detalle_pedido dp " +
            "  inner join articulo am on am.id = dp.articulo_id " +
            "  inner join pedido p on dp.pedido_id = p.id " +
            "  where CAST(p.fecha_pedido AS DATE) between :initialDate and :endDate " +
            "  and p.sucursal_id = :idSucursal and p.estado_pedido = 'COMPLETADO' " +
            "  group by am.denominacion " +
            "  union all " +
            "  select a.denominacion as denominacion, count(dp.id) * pd.cantidad as countVentas " +
            "  from detalle_pedido dp " +
            "  inner join promocion pr on pr.id = dp.promocion_id " +
            "  inner join promocion_detalle pd on pd.promocion_id = pr.id " +
            "  inner join articulo a on a.id = pd.articulo_id " +
            "  inner join pedido p on dp.pedido_id = p.id " +
            "  where CAST(p.fecha_pedido AS DATE) between :initialDate and :endDate " +
            "  and p.sucursal_id = :idSucursal and p.estado_pedido = 'COMPLETADO' " +
            "  group by a.denominacion, dp.id, pd.cantidad " +
            ") as subquery " +
            "group by denominacion " +
            "order by countVentas desc",
            nativeQuery = true)
    List<RankingProductos> bestProducts(@Param("initialDate") LocalDate initialDate,
                                        @Param("endDate") LocalDate endDate,
                                        @Param("idSucursal") Long idSucursal);


    @Query("SELECT d FROM Pedido p JOIN p.detallePedidos d WHERE p.id = :idPedido AND p.eliminado=false")
    List<DetallePedido> findAllByPedidoId(@Param("idPedido")Long idPedido);


    @Query(value = "SELECT sucursal, denominacion, SUM(countVentas) AS countVentas " +
            "FROM ( " +
            "  SELECT s.nombre AS sucursal, am.denominacion AS denominacion, COUNT(dp.id) AS countVentas " +
            "  FROM detalle_pedido dp " +
            "  INNER JOIN articulo am ON am.id = dp.articulo_id " +
            "  INNER JOIN pedido p ON dp.pedido_id = p.id " +
            "  INNER JOIN sucursal s ON p.sucursal_id = s.id " +
            "  INNER JOIN empresa e ON s.empresa_id = e.id " +
            "  WHERE CAST(p.fecha_pedido AS DATE) BETWEEN :initialDate AND :endDate " +
            "  AND e.id = :idEmpresa " +
            "  AND p.estado_pedido = 'COMPLETADO' " +
            "  GROUP BY s.nombre, am.denominacion " +
            "  UNION ALL " +
            "  SELECT s.nombre AS sucursal, a.denominacion AS denominacion, COUNT(dp.id) * pd.cantidad AS countVentas " +
            "  FROM detalle_pedido dp " +
            "  INNER JOIN promocion pr ON pr.id = dp.promocion_id " +
            "  INNER JOIN promocion_detalle pd ON pd.promocion_id = pr.id " +
            "  INNER JOIN articulo a ON a.id = pd.articulo_id " +
            "  INNER JOIN pedido p ON dp.pedido_id = p.id " +
            "  INNER JOIN sucursal s ON p.sucursal_id = s.id " +
            "  INNER JOIN empresa e ON s.empresa_id = e.id " +
            "  WHERE CAST(p.fecha_pedido AS DATE) BETWEEN :initialDate AND :endDate " +
            "  AND e.id = :idEmpresa " +
            "  AND p.estado_pedido = 'COMPLETADO' " +
            "  GROUP BY s.nombre, a.denominacion, dp.id, pd.cantidad " +
            ") AS subquery " +
            "GROUP BY sucursal, denominacion " +
            "ORDER BY countVentas DESC",
            nativeQuery = true)
    List<RankingProductos> bestProductsByEmpresa(@Param("initialDate") LocalDate initialDate,
                                                 @Param("endDate") LocalDate endDate,
                                                 @Param("idEmpresa") Long idEmpresa);




}
