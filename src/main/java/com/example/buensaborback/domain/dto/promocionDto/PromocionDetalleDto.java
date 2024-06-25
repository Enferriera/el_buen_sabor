package com.example.buensaborback.domain.dto.promocionDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PromocionDetalleDto extends BaseDto {
    private int cantidad;
    private ArticuloShortDto articulo;
}
