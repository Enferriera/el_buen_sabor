package com.example.buensaborback.domain.dto.articuloInsumoDto;

import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticuloInsumoDto extends ArticuloDto {
    private Double precioCompra;
    private Boolean esParaElaborar;
    private Set<StockInsumoShortDto>  stocksInsumo;
}
