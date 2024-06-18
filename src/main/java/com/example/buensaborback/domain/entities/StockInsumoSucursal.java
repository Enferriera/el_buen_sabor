package com.example.buensaborback.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class StockInsumoSucursal extends Base{
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;
    @ManyToOne
    @JoinColumn(name="articuloInsumo")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name="sucursal")
    private Sucursal sucursal;
}
