package com.example.buensaborback.domain.dto.articuloInsumoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockInsumoShortDto extends BaseDto {
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private ArticuloInsumoShortDto articuloInsumo;
    private SucursalShortDto sucursal;
}
