package com.example.buensaborback.business.service;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PedidoService extends BaseService<Pedido, Long> {
   // void validarStock(Set<DetallePedido> detalles);

    List<Pedido> obtenerPedidosEnCocina(Long idSucursal);

    public void revertirStock(Pedido pedido) throws RuntimeException;
    public Pedido updateEstado(Long id, EstadoPedido estado) throws ServicioException;
    public  List<Pedido> findByEstadoPedidoAndSucursalId(EstadoPedido estado,Long idSucursal);



    Pedido findById(Long id);

    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);

}
