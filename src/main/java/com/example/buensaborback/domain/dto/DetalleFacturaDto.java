package com.example.buensaborback.domain.dto;

import com.example.buensaborback.domain.dto.articuloDto.ArticuloShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetalleFacturaDto extends BaseDto {
    private Integer cantidad;

    private Double subtotal;

    private ArticuloShortDto articulo;
}
