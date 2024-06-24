package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.dto.Estadisticas.CostoGanancia;
import com.example.buensaborback.domain.dto.Estadisticas.IngresosDiarios;
import com.example.buensaborback.domain.dto.Estadisticas.IngresosMensuales;
import com.example.buensaborback.domain.dto.Estadisticas.PedidosCliente;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{
    @Query("SELECT p FROM Pedido p WHERE p.estadoPedido =:estado AND p.sucursal.id =:idSucursal")
    List<Pedido> findByEstadoPedidoAndSucursalId(@Param("estado")EstadoPedido estado, @Param("idSucursal")Long idSucursal);


    List<Pedido> findByClienteId(Long clienteId);

    @Query(value = "SELECT COUNT(p) FROM Pedido p WHERE p.fechaPedido BETWEEN :initialDate AND :endDate")
    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);

    @Query(value = "SELECT " +
            "SUM(dp.cantidad * ai.precioCompra) AS costos, " +
            "SUM(p.total) AS ganancias, " +
            "(SUM(p.total) - SUM(dp.cantidad * ai.precioCompra)) AS resultado " +
            "FROM Pedido p " +
            "JOIN p.detallePedidos dp " +
            "JOIN dp.articulo a " +
            "LEFT JOIN ArticuloInsumo ai ON a.id = ai.id " +
            "WHERE p.fechaPedido BETWEEN :initialDate AND :endDate " +
            "AND p.sucursal.id = :idSucursal AND p.estadoPedido='COMPLETADO'")
    CostoGanancia findCostosGananciasByFechaAndSucursal(@Param("initialDate") LocalDate initialDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("idSucursal") Long idSucursal);

    @Query(value = "SELECT " +
            "SUM(dp.cantidad * ai.precioCompra) AS costos, " +
            "SUM(p.total) AS ganancias, " +
            "(SUM(p.total) - SUM(dp.cantidad * ai.precioCompra)) AS resultado " +
            "FROM Pedido p " +
            "JOIN p.detallePedidos dp " +
            "JOIN dp.articulo a " +
            "LEFT JOIN ArticuloInsumo ai ON a.id = ai.id " +
            "JOIN p.sucursal s " +
            "JOIN s.empresa e " +
            "WHERE p.fechaPedido BETWEEN :initialDate AND :endDate " +
            "AND e.id = :idEmpresa AND p.estadoPedido='COMPLETADO'")
    CostoGanancia findCostosGananciasByFechaAndEmpresa(@Param("initialDate") LocalDate initialDate,
                                                       @Param("endDate") LocalDate endDate,
                                                       @Param("idEmpresa") Long idEmpresa);

    @Query(value = "SELECT DATE(p.fecha_pedido) AS fecha, SUM(p.total) AS ingresos " +
            "FROM pedido p " +
            "WHERE p.fecha_pedido BETWEEN :initialDate AND :endDate " +
            "AND p.sucursal_id = :idSucursal AND p.estado_pedido='COMPLETADO' " +
            "GROUP BY DATE(p.fecha_pedido)", nativeQuery = true)
    List<IngresosDiarios> ingresosDiariosPorSucursal(@Param("initialDate") Date initialDate,
                                                     @Param("endDate") Date endDate,
                                                     @Param("idSucursal") Long idSucursal);

    @Query(value = "SELECT FORMATDATETIME(p.fecha_pedido, 'yyyy-MM') AS mes, SUM(p.total) AS ingresos " +
            "FROM Pedido p " +
            "WHERE p.fecha_pedido BETWEEN :startDate AND :endDate " +
            "AND p.sucursal_id = :idSucursal AND p.estado_pedido='COMPLETADO'" +
            "GROUP BY FORMATDATETIME(p.fecha_pedido, 'yyyy-MM')", nativeQuery = true)
    List<IngresosMensuales> ingresosMensualesPorSucursal(@Param("startDate") Date startDate,
                                                         @Param("endDate") Date endDate,
                                                         @Param("idSucursal") Long idSucursal);

    @Query(value = "SELECT DATE(p.fecha_pedido) AS fecha, SUM(p.total) AS ingresos " +
            "FROM pedido p " +
            "JOIN sucursal s ON p.sucursal_id = s.id " +
            "WHERE p.fecha_pedido BETWEEN :initialDate AND :endDate " +
            "AND s.empresa_id = :idEmpresa AND p.estado_pedido = 'COMPLETADO' " +
            "GROUP BY DATE(p.fecha_pedido)", nativeQuery = true)
    List<IngresosDiarios> ingresosDiariosPorEmpresa(@Param("initialDate") Date initialDate,
                                                    @Param("endDate") Date endDate,
                                                    @Param("idEmpresa") Long idEmpresa);

    @Query(value = "SELECT FORMATDATETIME(p.fecha_pedido, 'yyyy-MM') AS mes, SUM(p.total) AS ingresos " +
            "FROM Pedido p " +
            "JOIN sucursal s ON p.sucursal_id = s.id " +
            "WHERE p.fecha_pedido BETWEEN :startDate AND :endDate " +
            "AND s.empresa_id = :idEmpresa AND p.estado_pedido = 'COMPLETADO' " +
            "GROUP BY FORMATDATETIME(p.fecha_pedido, 'yyyy-MM')", nativeQuery = true)
    List<IngresosMensuales> ingresosMensualesPorEmpresa(@Param("startDate") Date startDate,
                                                        @Param("endDate") Date endDate,
                                                        @Param("idEmpresa") Long idEmpresa);


    @Query("SELECT p.cliente.email AS email, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "WHERE CAST(p.fechaPedido AS DATE) BETWEEN :startDate AND :endDate " +
            "AND p.sucursal.id = :idSucursal AND p.estadoPedido='COMPLETADO' " +
            "GROUP BY p.cliente.email " +
            "ORDER BY cantidadPedidos DESC")
    List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(@Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate,
                                                                @Param("idSucursal") Long idSucursal);

    @Query("SELECT p.cliente.email AS email, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "JOIN p.sucursal s " +
            "WHERE CAST(p.fechaPedido AS DATE) BETWEEN :startDate AND :endDate " +
            "AND s.empresa.id = :idEmpresa AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY p.cliente.email " +
            "ORDER BY cantidadPedidos DESC")
    List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(@Param("startDate") LocalDate startDate,
                                                               @Param("endDate") LocalDate endDate,
                                                               @Param("idEmpresa") Long idEmpresa);
}
