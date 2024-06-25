package com.example.buensaborback.domain.dto.pedidoDto;

import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
import com.example.buensaborback.domain.dto.personaDto.PersonaShortDto;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PedidoDto extends BaseDto {

    private LocalTime horaEstimadaFinalizacion;
    private Double total;

    private EstadoPedido estadoPedido;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private DomicilioDto domicilio;

    private SucursalShortDto sucursal;

    private Factura factura;

    private PersonaShortDto cliente;

    private Set<DetallePedidoDto> detallePedidos = new HashSet<>();

    private PersonaShortDto empleado;
}
