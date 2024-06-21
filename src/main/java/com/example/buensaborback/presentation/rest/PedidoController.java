package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.PedidoFacadeImpl;
import com.example.buensaborback.business.service.PedidoService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto,PedidoDto,Long, PedidoFacadeImpl> {
    public PedidoController(PedidoFacadeImpl facade, PedidoService pedidoService) {
        super(facade);

    }

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}/nextState")
    public Set<EstadoPedido> getEstadosPosibles(@PathVariable Long id) {
        Pedido pedido = pedidoService.getById(id);
        EstadoPedido estadoActual = pedido.getEstadoPedido();
        FormaPago formaPago = pedido.getFormaPago();
        return estadoActual.getValidNextStates(formaPago);
    }
}