package com.example.buensaborback.domain.dto.personaDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.ImagenPersonaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonaDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private ImagenPersonaDto imagenPersona;
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;


}
