package com.example.buensaborback.domain.dto.articuloInsumoDto;

import com.example.buensaborback.domain.dto.articuloDto.ArticuloCreateDto;
import com.example.buensaborback.domain.entities.StockInsumoSucursal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloInsumoCreateDto extends ArticuloCreateDto {
    private Double precioCompra;
    private Boolean esParaElaborar;
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
   // private Set<StockCreateSucursalDto> stocksInsumo;
}
