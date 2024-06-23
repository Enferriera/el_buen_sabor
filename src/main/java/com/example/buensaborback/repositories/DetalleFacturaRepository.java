package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.DetalleFactura;
import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends BaseRepository<DetalleFactura, Long> {

}
