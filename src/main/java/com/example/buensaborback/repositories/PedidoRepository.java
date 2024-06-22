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
    List<Pedido> findByEstadoPedido(EstadoPedido estado);

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
            "WHERE p.fechaPedido BETWEEN :initialDate AND :endDate")
    CostoGanancia findCostosGananciasByFecha(LocalDate initialDate, LocalDate endDate);

    @Query(value = "SELECT TRUNCATE(p.fecha_pedido) AS fecha, SUM(p.total) AS ingresos " +
            "FROM pedido p " +
            "WHERE p.fecha_pedido BETWEEN :initialDate AND :endDate " +
            "GROUP BY TRUNCATE(p.fecha_pedido)", nativeQuery = true)
    List<IngresosDiarios> ingresosDiarios(@Param("initialDate") Date initialDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT FORMATDATETIME(p.fecha_pedido, 'yyyy-MM') AS mes, SUM(p.total) AS ingresos " +
            "FROM Pedido p " +
            "WHERE p.fecha_pedido BETWEEN :startDate AND :endDate " +
            "GROUP BY FORMATDATETIME(p.fecha_pedido, 'yyyy-MM')", nativeQuery = true)
    List<IngresosMensuales> ingresosMensuales(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT p.cliente.email AS email, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "WHERE CAST(p.fechaPedido AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY p.cliente.email " +
            "ORDER BY cantidadPedidos DESC")
    List<PedidosCliente> findCantidadPedidosPorCliente(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
