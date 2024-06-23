package com.example.buensaborback.business.service;

import com.example.buensaborback.domain.dto.Estadisticas.*;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EstadisticaService {
    List<RankingProductos> bestProducts(Date initialDate, Date endDate, Long idSucursal);
    List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate);
    List<IngresosMensuales> ingresosMensuales(Date initialDate, Date endDate);
    CostoGanancia findCostosGananciasByFecha(LocalDate initialDate, LocalDate endDate);
    List<PedidosCliente> findCantidadPedidosPorCliente(LocalDate startDate, LocalDate endDate);
    byte[] generarReporteExcel(Date fechaDesde, Date fechaHasta,Long idSucursal) throws IOException;
    List<RankingProductos> bestProductsByEmpresa(Date initialDate, Date endDate,Long idEmpresa);
}
