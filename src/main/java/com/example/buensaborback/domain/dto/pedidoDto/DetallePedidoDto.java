package com.example.buensaborback.domain.dto.pedidoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloShortDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetallePedidoDto extends BaseDto {
    private Integer cantidad;
    private Double subTotal;
    private ArticuloShortDto articulo;
    private PromocionShortDto promocion;




}
