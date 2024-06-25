package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.exceptions.ServicioException;
import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.PedidoFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.PedidoMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.PedidoService;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoFacadeImpl extends BaseFacadeImp<Pedido, PedidoDto, PedidoDto, Long> implements PedidoFacade {
    public PedidoFacadeImpl(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDto, PedidoDto> baseMapper, PedidoMapper pedidoMapper) {
        super(baseService, baseMapper);

    }

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoService pedidoService;

    @Override
    @Transactional
    public PedidoDto updateEstado(Long id, EstadoPedido estado)  {
        return pedidoMapper.toDTO(pedidoService.updateEstado(id,estado));
    }

    @Override
    @Transactional
    public List<PedidoDto> findByEstadoPedido(EstadoPedido estado, Long idSucursal) {
        List<Pedido> pedidos = pedidoService.findByEstadoPedidoAndSucursalId(estado,idSucursal);
        return pedidos.stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PedidoDto> buscarPedidosIngresoCaja(Long idSucursal){
        return pedidoMapper.toDTOsList(pedidoService.buscarPedidosIngresoCaja(idSucursal));
    }

    @Override
    @Transactional
    public List<PedidoDto> obtenerPedidosEnCocina(Long idSucursal){
        return pedidoMapper.toDTOsList(pedidoService.obtenerPedidosEnCocina(idSucursal));
    }

    @Override
    @Transactional
    public List<PedidoDto> obtenerPedidosEnDelivery(Long idSucursal){
        return pedidoMapper.toDTOsList(pedidoService.obtenerPedidosEnDelivery(idSucursal));
    }

    @Override
    @Transactional
    public List<PedidoDto> buscarPedidosPendienteEntrega(Long idSucursal){
        return pedidoMapper.toDTOsList(pedidoService.buscarPedidosPendienteEntrega(idSucursal));
    }

    @Override
    @Transactional
    public List<PedidoDto> findPedidoBySucursalId(LocalDate fechaInicio, LocalDate fechaFin, Long idSucursal) {
        return pedidoMapper.toDTOsList(pedidoService.findPedidoBySucursalId(fechaInicio, fechaFin, idSucursal));
    }
}
