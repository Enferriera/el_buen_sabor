package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends BaseRepository<Factura,Long>{

    @Query("SELECT f FROM Pedido p JOIN p.factura f WHERE p.id = :idPedido")
    Optional<Factura> findByPedidoId(Long idPedido);

    @Query("SELECT CASE WHEN COUNT(p.id) > 0 THEN TRUE ELSE FALSE END FROM Pedido p WHERE p.id = :idPedido AND p.eliminado = FALSE AND p.factura IS NOT NULL")
    boolean existsByPedidoId(@Param("idPedido")Long idPedido);
}
