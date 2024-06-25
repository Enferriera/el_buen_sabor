package com.example.buensaborback.business.service;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PedidoService extends BaseService<Pedido, Long> {
   // void validarStock(Set<DetallePedido> detalles);

    List<Pedido> obtenerPedidosEnCocina(Long idSucursal);

    public void revertirStock(Pedido pedido);
    public Pedido updateEstado(Long id, EstadoPedido estado) ;
    public  List<Pedido> findByEstadoPedidoAndSucursalId(EstadoPedido estado,Long idSucursal);

    List<Pedido> obtenerPedidosEnDelivery(Long idSucursal);
    List<Pedido> buscarPedidosIngresoCaja(Long idSucursal);
    List<Pedido> buscarPedidosPendienteEntrega(Long idSucursal);


    Pedido findById(Long id);

    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);

    List<Pedido> findPedidoBySucursalId(LocalDate fechaInicio,LocalDate fechaFin,Long idSucursal);
}
