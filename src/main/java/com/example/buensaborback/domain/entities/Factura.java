package com.example.buensaborback.domain.entities;


import com.example.buensaborback.domain.enums.FormaPago;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Factura extends Base{
    private LocalDate fechaFacturacion;
    private int montoDescuento;
    private FormaPago formaPago;
    private Double totalVenta;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    private Set<DetalleFactura> detalleFacturas= new HashSet<>();


}
