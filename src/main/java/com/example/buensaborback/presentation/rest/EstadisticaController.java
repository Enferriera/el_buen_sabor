package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.EstadisticaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin(origins="*")
public class EstadisticaController {

    @Autowired
    private EstadisticaFacade estadisticasFacade;

    @GetMapping("/ranking/{idSucursal}")
    public ResponseEntity<?> rankin (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.bestProducts(fechaDesde, fechaHasta,idSucursal));
    }

    @GetMapping("/recaudacionesDiarias")
    public ResponseEntity<?> recaudacionesDiarias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(estadisticasFacade.ingresosDiarios(fechaDesde, fechaHasta));
    }

    @GetMapping("/recaudacionesMensuales")
    public ResponseEntity<?> recaudacionesMensuales (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta){
        return ResponseEntity.ok(estadisticasFacade.ingresosMensuales(fechaDesde, fechaHasta));
    }

    @GetMapping("/costosGanancias")
    public ResponseEntity<?> costosGanancias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta){
        return ResponseEntity.ok(estadisticasFacade.findCostosGananciasByFecha(fechaDesde, fechaHasta));
    }

    @GetMapping("/pedidosCliente")
    public ResponseEntity<?> pedidosCliente (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta){
        return ResponseEntity.ok(estadisticasFacade.findCantidadPedidosPorCliente(fechaDesde, fechaHasta));
    }

    @GetMapping("/excel/{idSucursal}")
    public ResponseEntity<?> excel (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
     @PathVariable Long idSucursal) throws IOException {
        byte[] excelContent = estadisticasFacade.generarReporteExcel(fechaDesde, fechaHasta,idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticas.xls");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }

    @GetMapping("/rankingEmpresa/{idEmpresa}")
    public ResponseEntity<?> rankinEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.bestProductsByEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

}
