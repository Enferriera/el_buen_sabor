package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.EstadisticaFacade;
import com.example.buensaborback.business.service.EstadisticaService;
import com.example.buensaborback.domain.dto.Estadisticas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

import java.util.List;

@Service
public class EstadisticaFacadeImpl implements EstadisticaFacade {
    @Autowired
    private EstadisticaService estadisticasService;

    @Override
    public List<RankingProductos> bestProducts(LocalDate initialDate, LocalDate endDate, Long idSucursal) {
        return estadisticasService.bestProducts(initialDate, endDate, idSucursal);
    }

    @Override
    public List<IngresosDiarios> ingresosDiariosPorSucursal(LocalDate initialDate, LocalDate endDate, Long idSucursal){
        return estadisticasService.ingresosDiariosPorSucursal(initialDate, endDate,idSucursal);
    }

    @Override
    public List<IngresosMensuales> ingresosMensualesPorSucursal(LocalDate startDate, LocalDate endDate, Long idSucursal) {
        return estadisticasService.ingresosMensualesPorSucursal(startDate, endDate,idSucursal);
    }

    @Override
    public List<IngresosDiarios> ingresosDiariosPorEmpresa(LocalDate initialDate, LocalDate endDate, Long idEmpresa){
        return estadisticasService.ingresosDiariosPorEmpresa(initialDate,endDate,idEmpresa);
    }

    @Override
    public List<IngresosMensuales> ingresosMensualesPorEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa){
        return estadisticasService.ingresosMensualesPorEmpresa(startDate,endDate,idEmpresa);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(LocalDate startDate,LocalDate endDate,Long idSucursal){
        return estadisticasService.findCantidadPedidosPorClienteYSucursal(startDate,endDate,idSucursal);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa){
        return estadisticasService.findCantidadPedidosPorClienteYEmpresa(startDate,endDate,idEmpresa);
    }

    @Override
    public  CostoGanancia findCostosGananciasByFechaAndSucursal(LocalDate initialDate,LocalDate endDate,Long idSucursal) {
        return estadisticasService.findCostosGananciasByFechaAndSucursal(initialDate, endDate,idSucursal);
    }

    @Override
    public CostoGanancia findCostosGananciasByFechaAndEmpresa(LocalDate initialDate,LocalDate endDate, Long idEmpresa){
        return estadisticasService.findCostosGananciasByFechaAndEmpresa(initialDate,endDate,idEmpresa);
    }



    @Override
    public byte[] generarReporteExcelPorSucursal(LocalDate fechaDesde, LocalDate fechaHasta, Long idSucursal) throws IOException {
        return estadisticasService.generarReporteExcelPorSucursal(fechaDesde,fechaHasta,idSucursal);
    }

    @Override
    public byte[] generarReporteExcelPorEmpresa(LocalDate fechaDesde, LocalDate fechaHasta, Long idEmpresa) throws IOException {
        return estadisticasService.generarReporteExcelPorEmpresa(fechaDesde,fechaHasta,idEmpresa);
    }

    @Override
    public List<RankingProductos> bestProductsByEmpresa(LocalDate initialDate, LocalDate endDate, Long idEmpresa){
        return estadisticasService.bestProductsByEmpresa(initialDate,endDate, idEmpresa);
    }
}
