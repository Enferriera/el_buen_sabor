package com.example.buensaborback.domain.dto.promocionDto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PromocionDetalleCreateDto extends BaseDto {
    private int cantidad;
    private Long idArticulo;
}
