package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Usuario extends Base{
    private String auth0Id;
    private String username;
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
