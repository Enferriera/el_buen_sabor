package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.EstadisticaService;
import com.example.buensaborback.domain.dto.Estadisticas.*;
import com.example.buensaborback.repositories.DetallePedidoRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<RankingProductos> bestProducts(LocalDate initialDate, LocalDate endDate, Long idSucursal) {
        return detallePedidoRepository.bestProducts(initialDate, endDate, idSucursal);
    }

    @Override
    public List<IngresosDiarios> ingresosDiariosPorSucursal(LocalDate initialDate, LocalDate endDate, Long idSucursal){
        return pedidoRepository.ingresosDiariosPorSucursal(initialDate, endDate,idSucursal);
    }

    @Override
    public List<IngresosMensuales> ingresosMensualesPorSucursal(LocalDate startDate, LocalDate endDate, Long idSucursal) {
        return pedidoRepository.ingresosMensualesPorSucursal(startDate, endDate,idSucursal);
    }

    @Override
   public List<IngresosDiarios> ingresosDiariosPorEmpresa(LocalDate initialDate, LocalDate endDate, Long idEmpresa){
        return pedidoRepository.ingresosDiariosPorEmpresa(initialDate,endDate,idEmpresa);
    }

    @Override
    public List<IngresosMensuales> ingresosMensualesPorEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa){
        return pedidoRepository.ingresosMensualesPorEmpresa(startDate,endDate,idEmpresa);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorClienteYSucursal(LocalDate startDate,LocalDate endDate,Long idSucursal){
        return pedidoRepository.findCantidadPedidosPorClienteYSucursal(startDate,endDate,idSucursal);
    }

    @Override
    public List<PedidosCliente> findCantidadPedidosPorClienteYEmpresa(LocalDate startDate, LocalDate endDate, Long idEmpresa){
        return pedidoRepository.findCantidadPedidosPorClienteYEmpresa(startDate,endDate,idEmpresa);
    }

    @Override
    public  CostoGanancia findCostosGananciasByFechaAndSucursal(LocalDate initialDate,LocalDate endDate,Long idSucursal){
        return pedidoRepository.findCostosGananciasByFechaAndSucursal(initialDate,endDate,idSucursal);
    }



    @Override
    public byte[] generarReporteExcelPorSucursal(LocalDate fechaDesde, LocalDate fechaHasta,Long idSucursal) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Ranking de comidas");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Denominacion", "Cantidad Vendida"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        List<RankingProductos> ranking = bestProducts(fechaDesde, fechaHasta,idSucursal);

        int rowNum = 1;
        for (RankingProductos r : ranking){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getDenominacion());
            row.createCell(1).setCellValue(r.getCountVentas());
        }

        // Autosize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        Sheet sheet2 = workbook.createSheet("Ingresos Diarios");

        // Crear encabezado
        Row headerRowIngresosDiarios = sheet2.createRow(0);
        String[] headersIngresosDiarios = {"Fecha", "Ingresos"};
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            Cell cell = headerRowIngresosDiarios.createCell(i);
            cell.setCellValue(headersIngresosDiarios[i]);
        }

        List<IngresosDiarios> ingresosDiarios = ingresosDiariosPorSucursal(fechaDesde, fechaHasta, idSucursal);

        rowNum = 1;
        for (IngresosDiarios r : ingresosDiarios){
            Row row = sheet2.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getFecha());
            row.createCell(1).setCellValue(r.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            sheet2.autoSizeColumn(i);
        }

        Sheet sheet3 = workbook.createSheet("Ingresos Mensuales");

        // Crear encabezado
        Row headerRowIngresosMensuales = sheet3.createRow(0);
        String[] headersIngresosMensuales = {"Mes", "Ingresos"};
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            Cell cell = headerRowIngresosMensuales.createCell(i);
            cell.setCellValue(headersIngresosMensuales[i]);
        }

        List<IngresosMensuales> ingresosMensuales = ingresosMensualesPorSucursal(fechaDesde, fechaHasta,idSucursal);

        rowNum = 1;
        for (IngresosMensuales r : ingresosMensuales){
            Row row = sheet3.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getMes());
            row.createCell(1).setCellValue(r.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            sheet3.autoSizeColumn(i);
        }

        Sheet sheet4 = workbook.createSheet("Pedidos por Cliente");

        // Crear encabezado
        Row headerRowPedidosClientes= sheet4.createRow(0);
        String[] headersPedidoClientes = {"Email cliente", "Cantidad de pedidos"};
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            Cell cell = headerRowPedidosClientes.createCell(i);
            cell.setCellValue(headersPedidoClientes[i]);
        }

        LocalDate dateInicio = fechaDesde;
        LocalDate dateFin = fechaHasta;

        List<PedidosCliente> pedidosClientes = findCantidadPedidosPorClienteYSucursal(dateInicio, dateFin,idSucursal);

        rowNum = 1;
        for (PedidosCliente r : pedidosClientes){
            Row row = sheet4.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getEmail());
            row.createCell(1).setCellValue(r.getCantidadPedidos());
        }

        // Autosize columns
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            sheet4.autoSizeColumn(i);
        }

        Sheet sheet5 = workbook.createSheet("Monto de Ganancia");

        // Crear encabezado
        Row headerRowGanancia= sheet5.createRow(0);
        String[] headersGanancia = {"Costo", "Ganancia", "Resultado"};
        for (int i = 0; i < headersGanancia.length; i++) {
            Cell cell = headerRowGanancia.createCell(i);
            cell.setCellValue(headersGanancia[i]);
        }

        CostoGanancia costoGanancias = findCostosGananciasByFechaAndSucursal(dateInicio, dateFin,idSucursal);

        rowNum = 1;
        Row row = sheet5.createRow(rowNum++);
        row.createCell(0).setCellValue((costoGanancias.getCostos() == null) ? 0 :costoGanancias.getCostos() );
        row.createCell(1).setCellValue((costoGanancias.getGanancias() == null) ? 0 : costoGanancias.getGanancias());
        row.createCell(2).setCellValue((costoGanancias.getResultado() == null ) ? 0 : costoGanancias.getResultado());


        // Autosize columns
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            sheet5.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }

    @Override
    public byte[] generarReporteExcelPorEmpresa(LocalDate fechaDesde, LocalDate fechaHasta,Long idEmpresa) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Ranking de comidas");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Denominacion", "Cantidad Vendida"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        List<RankingProductos> ranking = bestProductsByEmpresa(fechaDesde, fechaHasta,idEmpresa);

        int rowNum = 1;
        for (RankingProductos r : ranking){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getDenominacion());
            row.createCell(1).setCellValue(r.getCountVentas());
        }

        // Autosize columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        Sheet sheet2 = workbook.createSheet("Ingresos Diarios");

        // Crear encabezado
        Row headerRowIngresosDiarios = sheet2.createRow(0);
        String[] headersIngresosDiarios = {"Fecha", "Ingresos"};
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            Cell cell = headerRowIngresosDiarios.createCell(i);
            cell.setCellValue(headersIngresosDiarios[i]);
        }

        List<IngresosDiarios> ingresosDiarios = ingresosDiariosPorEmpresa(fechaDesde, fechaHasta, idEmpresa);

        rowNum = 1;
        for (IngresosDiarios r : ingresosDiarios){
            Row row = sheet2.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getFecha());
            row.createCell(1).setCellValue(r.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosDiarios.length; i++) {
            sheet2.autoSizeColumn(i);
        }

        Sheet sheet3 = workbook.createSheet("Ingresos Mensuales");

        // Crear encabezado
        Row headerRowIngresosMensuales = sheet3.createRow(0);
        String[] headersIngresosMensuales = {"Mes", "Ingresos"};
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            Cell cell = headerRowIngresosMensuales.createCell(i);
            cell.setCellValue(headersIngresosMensuales[i]);
        }

        List<IngresosMensuales> ingresosMensuales = ingresosMensualesPorEmpresa(fechaDesde, fechaHasta,idEmpresa);

        rowNum = 1;
        for (IngresosMensuales r : ingresosMensuales){
            Row row = sheet3.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getMes());
            row.createCell(1).setCellValue(r.getIngresos());
        }

        // Autosize columns
        for (int i = 0; i < headersIngresosMensuales.length; i++) {
            sheet3.autoSizeColumn(i);
        }

        Sheet sheet4 = workbook.createSheet("Pedidos por Cliente");

        // Crear encabezado
        Row headerRowPedidosClientes= sheet4.createRow(0);
        String[] headersPedidoClientes = {"Email cliente", "Cantidad de pedidos"};
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            Cell cell = headerRowPedidosClientes.createCell(i);
            cell.setCellValue(headersPedidoClientes[i]);
        }

        LocalDate dateInicio = fechaDesde;
        LocalDate dateFin = fechaHasta;

        List<PedidosCliente> pedidosClientes = findCantidadPedidosPorClienteYEmpresa(dateInicio, dateFin,idEmpresa);

        rowNum = 1;
        for (PedidosCliente r : pedidosClientes){
            Row row = sheet4.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getEmail());
            row.createCell(1).setCellValue(r.getCantidadPedidos());
        }

        // Autosize columns
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            sheet4.autoSizeColumn(i);
        }

        Sheet sheet5 = workbook.createSheet("Monto de Ganancia");

        // Crear encabezado
        Row headerRowGanancia= sheet5.createRow(0);
        String[] headersGanancia = {"Costo", "Ganancia", "Resultado"};
        for (int i = 0; i < headersGanancia.length; i++) {
            Cell cell = headerRowGanancia.createCell(i);
            cell.setCellValue(headersGanancia[i]);
        }

        CostoGanancia costoGanancias = findCostosGananciasByFechaAndEmpresa(dateInicio, dateFin,idEmpresa);

        rowNum = 1;
        Row row = sheet5.createRow(rowNum++);
        row.createCell(0).setCellValue((costoGanancias.getCostos() == null) ? 0 :costoGanancias.getCostos() );
        row.createCell(1).setCellValue((costoGanancias.getGanancias() == null) ? 0 : costoGanancias.getGanancias());
        row.createCell(2).setCellValue((costoGanancias.getResultado() == null ) ? 0 : costoGanancias.getResultado());


        // Autosize columns
        for (int i = 0; i < headersPedidoClientes.length; i++) {
            sheet5.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();

        return baos.toByteArray();
    }



    @Override
    @Transactional
   public List<RankingProductos> bestProductsByEmpresa(LocalDate initialDate, LocalDate endDate, Long idEmpresa){
        return detallePedidoRepository.bestProductsByEmpresa(initialDate,endDate, idEmpresa);
    }

    @Override
    @Transactional
    public  CostoGanancia findCostosGananciasByFechaAndEmpresa(LocalDate initialDate,LocalDate endDate, Long idEmpresa){
        return pedidoRepository.findCostosGananciasByFechaAndEmpresa(initialDate,endDate,idEmpresa);
    }


}
