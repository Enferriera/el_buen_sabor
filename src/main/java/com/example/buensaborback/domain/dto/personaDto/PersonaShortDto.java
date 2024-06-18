package com.example.buensaborback.domain.dto.personaDto;

import com.example.buensaborback.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonaShortDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
}
