package com.example.buensaborback.domain.dto.SucursalDtos;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.dto.EmpresaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SucursalDto extends BaseDto {

    private String nombre;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioApertura;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss")
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;
    private String logo;

    private DomicilioDto domicilio;

    private EmpresaDto empresa;


}