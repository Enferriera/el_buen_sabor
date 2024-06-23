package com.example.buensaborback.domain.dto;

import com.example.buensaborback.domain.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FacturaDto extends BaseDto {
    private LocalDate fechaFacturacion;
    private int montoDescuento;
    private FormaPago formaPago;
    private Double totalVenta;
    private Set<DetalleFacturaDto> detalleFacturas;
}
