package com.example.buensaborback.business.mapper;

import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.business.service.SucursalService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.Estado;
import com.example.buensaborback.domain.enums.FormaPago;
import com.example.buensaborback.domain.enums.TipoEnvio;
import org.mapstruct.Mapper;

import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class,EmpleadoMapper.class, ClienteService.class, DomicilioMapper.class, FormaPago.class, Estado.class, TipoEnvio.class, SucursalMapper.class, SucursalService.class, UsuarioMapper.class, ClienteMapper.class, ArticuloMapper.class, ArticuloMapper.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoDto> {


    @Named("stringToFormaPago")
    default FormaPago stringToFormaPago(String formaPago) {
        return FormaPago.valueOf(formaPago);
    }

    default String formaPagoToString(FormaPago formaPago) {
        return formaPago.name();
    }

    @Named("stringToTipoEnvio")
    default TipoEnvio stringToTipoEnvio(String tipoEnvio) {
        return TipoEnvio.valueOf(tipoEnvio);
    }

    default String tipoEnvioToString(TipoEnvio tipoEnvio) {
        return tipoEnvio.name();
    }

    @Named("stringToEstado")
    default Estado stringToEstado(String estado) {
        return Estado.valueOf(estado);
    }

    default String EstadoToString(Estado estado) {
        return estado.name();
    }
}
