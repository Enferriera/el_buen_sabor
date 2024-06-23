package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class UsuarioCliente extends Base{
    public String email;
    private String claveEncriptada;

    public void setClave(String clave) {
        try {
            // Selecciona el algoritmo de encriptación (por ejemplo, MD5)
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Convierte la contraseña en bytes
            byte[] messageDigest = md.digest(clave.getBytes());
            // Convierte el array de bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            // Establece la contraseña encriptada
            this.claveEncriptada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
