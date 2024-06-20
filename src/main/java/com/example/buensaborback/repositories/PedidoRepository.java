package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{
    List<Pedido> findByEstadoPedido(EstadoPedido estado);

    @Query(value = "SELECT COUNT(p) FROM Pedido p WHERE p.fechaPedido BETWEEN :initialDate AND :endDate")
    Long contarPedidosEnRango(LocalDate initialDate, LocalDate endDate);
}
