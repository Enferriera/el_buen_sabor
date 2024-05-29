package com.example.buensaborback.domain.dto.articuloDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaDto;
import com.example.buensaborback.domain.dto.ImagenArticuloDto;
import com.example.buensaborback.domain.dto.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloCreateDto extends BaseDto {
    protected String denominacion;
    protected Double precioVenta;
    protected Set<ImagenArticuloDto> imagenes = new HashSet<>();
    protected Long idUnidadMedida;
    protected Long idCategoria;
}
