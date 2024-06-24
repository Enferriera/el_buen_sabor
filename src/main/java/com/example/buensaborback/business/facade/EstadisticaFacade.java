package com.example.buensaborback.business.facade;

import com.example.buensaborback.domain.dto.Estadisticas.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface EstadisticaFacade {
    List<RankingProductos> bestProducts(Date initialDate, Date endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiariosPorSucursal(Date initialDate, Date endDate, Long idSucursal);
    List<IngresosMensuales> ingresosMensualesPorSucursal(Date startDate, Date endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiariosPorEmpresa(Date initialDate, Date endDate, Long idEmpresa);
    List<IngresosMensuales> ingresosMensualesPorEmpresa(Date startDate, Date endDate, Long idEmpresa);
    List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(LocalDate startDate,LocalDate endDate,Long idSucursal);
    List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa);
    CostoGanancia findCostosGananciasByFechaAndSucursal(LocalDate initialDate,LocalDate endDate,Long idSucursal);
    CostoGanancia findCostosGananciasByFechaAndEmpresa(LocalDate initialDate,LocalDate endDate, Long idEmpresa);
    byte[] generarReporteExcelPorSucursal(Date fechaDesde, Date fechaHasta,Long idSucursal) throws IOException;
    byte[] generarReporteExcelPorEmpresa(Date fechaDesde, Date fechaHasta,Long idEmpresa) throws IOException;
    List<RankingProductos> bestProductsByEmpresa(Date initialDate, Date endDate,Long idEmpresa);
}
