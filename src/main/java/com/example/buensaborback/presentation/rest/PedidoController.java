package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Imp.PedidoFacadeImpl;
import com.example.buensaborback.business.service.PedidoService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins="*")
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

    @GetMapping("/pedidosEnCocinaPorSucursal/{idSucursal}")
    public ResponseEntity<List<PedidoDto>> pedidosEnCocinaSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.obtenerPedidosEnCocina(idSucursal));
    }

    @GetMapping("/pedidosEnDeliveryPorSucursal/{idSucursal}")
    public ResponseEntity<List<PedidoDto>> pedidosEnDeliverySucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.obtenerPedidosEnDelivery(idSucursal));
    }

    @GetMapping("/pedidosIngresoCajaPorSucursal/{idSucursal}")
    public ResponseEntity<List<PedidoDto>> pedidosIngresoCajaSucursal(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.buscarPedidosIngresoCaja(idSucursal));
    }

    @GetMapping("/pedidosPendienteEntregaCaja/{idSucursal}")
    public ResponseEntity<List<PedidoDto>> pedidosPendienteEntregaCaja(@PathVariable Long idSucursal){
        return ResponseEntity.ok().body(facade.buscarPedidosPendienteEntrega(idSucursal));
    }

    @PutMapping("/cambiaEstado/{id}")
    public ResponseEntity<?> cambiaEstado(@RequestParam String estadoPedido, @PathVariable Long id ) {

            System.out.println(estadoPedido);
            EstadoPedido estadoPedidoValido = EstadoPedido.valueOf(estadoPedido.toUpperCase());

            return ResponseEntity.ok(facade.updateEstado(id, estadoPedidoValido));

    }

    @GetMapping("/getPorEstadoYSucursal/{idSucursal}")
    public ResponseEntity<?> getByEstadoAndSucursal(@RequestParam String estadoPedido, @PathVariable Long idSucursal){

            EstadoPedido estado = EstadoPedido.valueOf(estadoPedido.toUpperCase());

            return ResponseEntity.ok(facade.findByEstadoPedido(estado,idSucursal));

    }
}