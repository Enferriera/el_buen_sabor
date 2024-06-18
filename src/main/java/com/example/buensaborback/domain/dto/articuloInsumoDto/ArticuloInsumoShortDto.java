package com.example.buensaborback.domain.dto.articuloInsumoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoShortDto extends BaseDto {
    private String denominacion;
}
