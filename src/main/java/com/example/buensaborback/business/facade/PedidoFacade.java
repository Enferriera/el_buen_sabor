package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.enums.EstadoPedido;

import java.util.List;

public interface PedidoFacade extends BaseFacade <PedidoDto, PedidoDto, Long> {

    public PedidoDto updateEstado(Long id, EstadoPedido estado) throws ServicioException;
    public List<PedidoDto> findByEstadoPedido(EstadoPedido estado, Long idSucursal);
}
