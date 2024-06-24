package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.DetalleFactura;
import com.example.buensaborback.domain.entities.DetallePedido;

public interface DetalleFacturaService extends BaseService<DetalleFactura, Long> {
    public DetalleFactura saveDetalleFromPedido(DetallePedido detallePedido);
}
