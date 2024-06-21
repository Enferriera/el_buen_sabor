package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;

import java.io.IOException;

public interface FacturaService extends BaseService<Factura,Long> {
    byte[] generarFacturaPDF(Pedido pedido) throws IOException;
}
