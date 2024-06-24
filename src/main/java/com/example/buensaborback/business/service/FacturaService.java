package com.example.buensaborback.business.service;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;

import java.io.IOException;

public interface FacturaService extends BaseService<Factura, Long> {
    byte[] generarFacturaPDF(Pedido pedido) throws IOException;
    public Factura saveFacturaAfterPagoEfectivo(Pedido pedido) ;
    public Factura crearNotaCredito(Pedido pedido) ;
}
