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
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{
    @Query("SELECT p FROM Pedido p WHERE p.estadoPedido =:estado AND p.sucursal.id =:idSucursal ORDER BY p.fechaPedido DESC")
    List<Pedido> findByEstadoPedidoAndSucursalId(@Param("estado")EstadoPedido estado, @Param("idSucursal")Long idSucursal);
    @Query("SELECT p FROM Pedido p WHERE ((p.estadoPedido = 'PENDIENTE_PAGO' AND p.formaPago='EFECTIVO') OR(p.estadoPedido = 'PAGADO' AND p.formaPago='MERCADO_PAGO'))AND p.sucursal.id =:idSucursal ORDER BY p.fechaPedido DESC")
    List<Pedido> buscarPedidosIngresoCaja(@Param("idSucursal")Long idSucursal);


    List<Pedido> findByClienteId(Long clienteId);

    @Query(value = "SELECT COUNT(p) FROM Pedido p WHERE p.fechaPedido BETWEEN :initialDate AND :endDate ")
    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);

    @Query(value = "SELECT " +
            "   SUM(p.totalCosto) AS costos, " +
            "   SUM(p.total) AS ganancias, " +
            "   SUM(p.total - p.totalCosto) AS resultado " +
            "FROM Pedido p " +
            "WHERE p.fechaPedido BETWEEN :initialDate AND :endDate " +
            "AND p.sucursal.id = :idSucursal " +
            "AND p.estadoPedido = 'COMPLETADO'")
    CostoGanancia findCostosGananciasByFechaAndSucursal(@Param("initialDate") LocalDate initialDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("idSucursal") Long idSucursal);


    @Query(value = "SELECT " +
            "   SUM(p.totalCosto) AS costos, " +
            "   SUM(p.total) AS ganancias, " +
            "   SUM(p.total - p.totalCosto) AS resultado " +
            "FROM Pedido p " +
            "JOIN p.sucursal s " +
            "WHERE p.fechaPedido BETWEEN :initialDate AND :endDate " +
            "AND s.empresa.id = :idEmpresa " +
            "AND p.estadoPedido = 'COMPLETADO'")
    CostoGanancia findCostosGananciasByFechaAndEmpresa(@Param("initialDate") LocalDate initialDate,
                                                       @Param("endDate") LocalDate endDate,
                                                       @Param("idEmpresa") Long idEmpresa);

    @Query("SELECT CAST(p.fechaPedido AS date) AS fecha, " +
            "SUM(COALESCE(p.total, 0) + COALESCE(pr.precioPromocional, 0)) AS ingresos " +
            "FROM Pedido p " +
            "LEFT JOIN p.detallePedidos dp " +
            "LEFT JOIN dp.promocion pr " +
            "WHERE p.fechaPedido BETWEEN :startDate AND :endDate " +
            "AND p.sucursal.id = :idSucursal " +
            "AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY CAST(p.fechaPedido AS date)")
    List<IngresosDiarios> ingresosDiariosPorSucursal(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate,
                                                     @Param("idSucursal") Long idSucursal);

    @Query("SELECT FUNCTION('YEAR', p.fechaPedido) AS anio, FUNCTION('MONTH', p.fechaPedido) AS mes, " +
            "SUM(COALESCE(p.total, 0) + COALESCE(pr.precioPromocional, 0)) AS ingresos " +
            "FROM Pedido p " +
            "LEFT JOIN p.detallePedidos dp " +
            "LEFT JOIN dp.promocion pr " +
            "WHERE p.fechaPedido BETWEEN :fechaDesde AND :fechaHasta " +
            "AND p.sucursal.id = :idSucursal " +
            "AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY FUNCTION('YEAR', p.fechaPedido), FUNCTION('MONTH', p.fechaPedido)")
    List<IngresosMensuales> ingresosMensualesPorSucursal(@Param("fechaDesde") LocalDate fechaDesde,
                                                @Param("fechaHasta") LocalDate fechaHasta,
                                                @Param("idSucursal") Long idSucursal);

    @Query("SELECT CAST(p.fechaPedido AS date) AS fecha, " +
            "SUM(COALESCE(p.total, 0) + COALESCE(pr.precioPromocional, 0)) AS ingresos " +
            "FROM Pedido p " +
            "LEFT JOIN p.detallePedidos dp " +
            "LEFT JOIN dp.promocion pr " +
            "JOIN p.sucursal s " +
            "WHERE p.fechaPedido BETWEEN :startDate AND :endDate " +
            "AND s.empresa.id = :idEmpresa " +  // Cambia la condición a s.empresa.id
            "AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY CAST(p.fechaPedido AS date)")
    List<IngresosDiarios> ingresosDiariosPorEmpresa(@Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate,
                                                    @Param("idEmpresa") Long idEmpresa);

    @Query("SELECT FUNCTION('YEAR', p.fechaPedido) AS anio, FUNCTION('MONTH', p.fechaPedido) AS mes, " +
            "SUM(COALESCE(p.total, 0) + COALESCE(pr.precioPromocional, 0)) AS ingresos " +
            "FROM Pedido p " +
            "LEFT JOIN p.detallePedidos dp " +
            "LEFT JOIN dp.promocion pr " +
            "JOIN p.sucursal s " +
            "WHERE p.fechaPedido BETWEEN :fechaDesde AND :fechaHasta " +
            "AND s.empresa.id = :idEmpresa " +  // Cambia la condición a s.empresa.id
            "AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY FUNCTION('YEAR', p.fechaPedido), FUNCTION('MONTH', p.fechaPedido)")
    List<IngresosMensuales> ingresosMensualesPorEmpresa(@Param("fechaDesde") LocalDate fechaDesde,
                                                        @Param("fechaHasta") LocalDate fechaHasta,
                                                        @Param("idEmpresa") Long idEmpresa);


    @Query("SELECT p.cliente.usuarioCliente.email AS email, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "WHERE CAST(p.fechaPedido AS DATE) BETWEEN :startDate AND :endDate " +
            "AND p.sucursal.id = :idSucursal AND p.estadoPedido='COMPLETADO' " +
            "GROUP BY p.cliente.usuarioCliente.email " +
            "ORDER BY cantidadPedidos DESC")
    List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(@Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate,
                                                                @Param("idSucursal") Long idSucursal);

    @Query("SELECT p.cliente.usuarioCliente.email AS email, COUNT(p) AS cantidadPedidos " +
            "FROM Pedido p " +
            "JOIN p.sucursal s " +
            "WHERE CAST(p.fechaPedido AS DATE) BETWEEN :startDate AND :endDate " +
            "AND s.empresa.id = :idEmpresa AND p.estadoPedido = 'COMPLETADO' " +
            "GROUP BY p.cliente.usuarioCliente.email " +
            "ORDER BY cantidadPedidos DESC")
    List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(@Param("startDate") LocalDate startDate,
                                                               @Param("endDate") LocalDate endDate,
                                                               @Param("idEmpresa") Long idEmpresa);

    @Query("SELECT p FROM Pedido p JOIN p.sucursal s WHERE CAST(p.fechaPedido AS DATE) " +
            "BETWEEN :startDate AND :endDate and s.id = :idSucursal")
    List<Pedido> findPedidosBySucursalId(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate,
                                                @Param("idSucursal") Long idSucursal);

}
