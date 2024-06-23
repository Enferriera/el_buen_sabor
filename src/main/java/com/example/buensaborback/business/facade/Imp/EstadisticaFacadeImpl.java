package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.EstadisticaFacade;
import com.example.buensaborback.business.service.EstadisticaService;
import com.example.buensaborback.domain.dto.Estadisticas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EstadisticaFacadeImpl implements EstadisticaFacade {
    @Autowired
    private EstadisticaService estadisticasService;

    @Override
    public List<RankingProductos> bestProducts(Date initialDate, Date endDate, Long idSucursal) {
        return estadisticasService.bestProducts(initialDate, endDate, idSucursal);
    }

    @Override
    public List<IngresosDiarios> ingresosDiarios(Date initialDate, Date endDate) {
        return estadisticasService.ingresosDiarios(initialDate, endDate);
    }

    @Override
    public List<IngresosMensuales> ingresosMensuales(Date initialDate, Date endDate) {
        return estadisticasService.ingresosMensuales(initialDate, endDate);
    }

    @Override
    public CostoGanancia findCostosGananciasByFecha(LocalDate initialDate, LocalDate endDate) {
        return estadisticasService.findCostosGananciasByFecha(initialDate, endDate);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorCliente(LocalDate startDate, LocalDate endDate) {
        return estadisticasService.findCantidadPedidosPorCliente(startDate, endDate);
    }

    @Override
    public byte[] generarReporteExcel(Date fechaDesde, Date fechaHasta, Long idSucursal) throws IOException {
        return estadisticasService.generarReporteExcel(fechaDesde,fechaHasta,idSucursal);
    }

    @Override
    public List<RankingProductos> bestProductsByEmpresa(Date initialDate, Date endDate, Long idEmpresa){
        return estadisticasService.bestProductsByEmpresa(initialDate,endDate, idEmpresa);
    }
}
