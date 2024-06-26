package com.example.buensaborback.domain.entities;


import com.example.buensaborback.domain.enums.FormaPago;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    private Double totalVenta;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="factura_id")
    @Builder.Default
    @JsonIgnore
    private Set<DetalleFactura> detalleFacturas= new HashSet<>();


}
