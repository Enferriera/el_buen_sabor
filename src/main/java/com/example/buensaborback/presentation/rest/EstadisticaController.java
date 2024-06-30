package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.EstadisticaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.MidiSystem;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin(origins="*")
public class EstadisticaController {

    @Autowired
    private EstadisticaFacade estadisticasFacade;

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/rankingSucursal/{idSucursal}")
    public ResponseEntity<?> rankinSucursal (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.bestProducts(fechaDesde, fechaHasta,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/rankingEmpresa/{idEmpresa}")
    public ResponseEntity<?> rankinEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.bestProductsByEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/recaudacionesDiariasSucursal/{idSucursal}")
    public ResponseEntity<?> recaudacionesDiariasSucursal (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.ingresosDiariosPorSucursal(fechaDesde, fechaHasta,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/recaudacionesDiariasEmpresa/{idEmpresa}")
    public ResponseEntity<?> recaudacionesDiariasEmpresa(
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.ingresosDiariosPorEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/recaudacionesMensualesSucursal/{idSucursal}")
    public ResponseEntity<?> recaudacionesMensualesSucursal (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.ingresosMensualesPorSucursal(fechaDesde, fechaHasta,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/recaudacionesMensualesEmpresa/{idEmpresa}")
    public ResponseEntity<?> recaudacionesMensualesEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.ingresosMensualesPorEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/costosGanancias/{idSucursal}")
    public ResponseEntity<?> costosGanancias (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.findCostosGananciasByFechaAndSucursal(fechaDesde, fechaHasta,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/costosGananciasEmpresa/{idEmpresa}")
    public ResponseEntity<?> costosGananciasEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.findCostosGananciasByFechaAndEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/pedidosClienteSucursal/{idSucursal}")
    public ResponseEntity<?> pedidosClienteSucursal (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idSucursal){
        return ResponseEntity.ok(estadisticasFacade.findCantidadPedidosPorClienteYSucursal(fechaDesde, fechaHasta,idSucursal));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/pedidosClienteEmpresa/{idEmpresa}")
    public ResponseEntity<?> pedidosClienteEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa){
        return ResponseEntity.ok(estadisticasFacade.findCantidadPedidosPorClienteYEmpresa(fechaDesde, fechaHasta,idEmpresa));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/excelSucursal/{idSucursal}")
    public ResponseEntity<?> excelSucursal (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
     @PathVariable Long idSucursal) throws IOException {
        byte[] excelContent = estadisticasFacade.generarReporteExcelPorSucursal(fechaDesde, fechaHasta,idSucursal);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticas.xls");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','GERENTE')")
    @GetMapping("/excelEmpresa/{idEmpresa}")
    public ResponseEntity<?> excelEmpresa (
            @RequestParam("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            @PathVariable Long idEmpresa) throws IOException {
        byte[] excelContent = estadisticasFacade.generarReporteExcelPorEmpresa(fechaDesde, fechaHasta,idEmpresa);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=estadisticas.xls");
        headers.setContentLength(excelContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelContent);
    }


}
