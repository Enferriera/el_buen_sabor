package com.example.buensaborback.domain.dto;

import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaLargeDto extends BaseDto {
    private String nombre;
    private String razonSocial;
    private Long cuil;
    private Set<SucursalDto> sucursales;
}
