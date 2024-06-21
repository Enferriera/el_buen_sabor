package com.example.buensaborback.domain.dto.articuloDto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloShortDto extends BaseDto {
    protected String denominacion;
    protected Double precioVenta;
    protected boolean habilitado;
    protected String codigo;

}
