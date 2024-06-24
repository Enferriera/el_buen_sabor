package com.example.buensaborback.business.facade;

import com.example.buensaborback.domain.dto.Estadisticas.*;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;


public interface EstadisticaFacade {
    List<RankingProductos> bestProducts(LocalDate initialDate, LocalDate endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiariosPorSucursal(LocalDate initialDate, LocalDate endDate, Long idSucursal);
    List<IngresosMensuales> ingresosMensualesPorSucursal(LocalDate startDate, LocalDate endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiariosPorEmpresa(LocalDate initialDate, LocalDate endDate, Long idEmpresa);
    List<IngresosMensuales> ingresosMensualesPorEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa);
    List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(LocalDate startDate,LocalDate endDate,Long idSucursal);
    List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa);
    CostoGanancia findCostosGananciasByFechaAndSucursal(LocalDate initialDate,LocalDate endDate,Long idSucursal);
    CostoGanancia findCostosGananciasByFechaAndEmpresa(LocalDate initialDate,LocalDate endDate, Long idEmpresa);
    byte[] generarReporteExcelPorSucursal(LocalDate fechaDesde, LocalDate fechaHasta,Long idSucursal) throws IOException;
    byte[] generarReporteExcelPorEmpresa(LocalDate fechaDesde, LocalDate fechaHasta,Long idEmpresa) throws IOException;
    List<RankingProductos> bestProductsByEmpresa(LocalDate initialDate, LocalDate endDate,Long idEmpresa);
}
