package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoFacade extends BaseFacade <PedidoDto, PedidoDto, Long> {

    public PedidoDto updateEstado(Long id, EstadoPedido estado) ;
    public List<PedidoDto> findByEstadoPedido(EstadoPedido estado, Long idSucursal);
    public List<PedidoDto> obtenerPedidosEnCocina(Long idSucursal);
    public List<PedidoDto> obtenerPedidosEnDelivery(Long idSucursal);
    public List<PedidoDto> buscarPedidosIngresoCaja(Long idSucursal);
    public List<PedidoDto> buscarPedidosPendienteEntrega(Long idSucursal);
    public List<PedidoDto> findPedidoBySucursalId(LocalDate fechaInicio, LocalDate fechaFin, Long idSucursal);
}
