package com.example.buensaborback.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioClienteDto extends BaseDto {
    private String email;
    private String claveEncriptada;
}
