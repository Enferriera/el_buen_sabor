package com.example.buensaborback.domain.dto.articuloInsumoDto;

import com.example.buensaborback.domain.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockCreateSucursalDto extends BaseDto {
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Long idArticuloInsumo;
    private Long idSucursal;
}
