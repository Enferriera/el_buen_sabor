package com.example.buensaborback.domain.dto.articulomanufacturadodto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloCreateDto;
import com.example.buensaborback.domain.dto.articuloDto.ArticuloDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoCreateDto extends ArticuloCreateDto {
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private Set<ArticuloManufacturadoDetalleCreateDto> articuloManufacturadoDetalles= new HashSet<>() ;

}
