package com.example.buensaborback.domain.dto.CategoriaDtos;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaPostDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private Set<Long> idSucursales;
    private Long idCategoriaPadre;

}
