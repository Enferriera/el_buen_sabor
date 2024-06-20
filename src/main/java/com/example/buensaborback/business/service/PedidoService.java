package com.example.buensaborback.business.service;

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

    boolean aplicarDescuento(Pedido pedido);


    void calcularTiempoEstimado(Pedido pedido);

    List<Pedido> obtenerPedidosEnCocina();

    int contarCocineros();

    Pedido cambiaEstado(EstadoPedido estado, Long id);

    List<Pedido> findByEstadoPedido(EstadoPedido estado);

    Optional<Pedido> findById(Long id);

    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);

}
