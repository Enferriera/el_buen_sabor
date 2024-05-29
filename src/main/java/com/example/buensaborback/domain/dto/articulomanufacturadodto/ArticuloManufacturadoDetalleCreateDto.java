package com.example.buensaborback.domain.dto.articulomanufacturadodto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoDetalleCreateDto extends BaseDto {
    private Integer cantidad;

    private Long idArticuloInsumo;
}
