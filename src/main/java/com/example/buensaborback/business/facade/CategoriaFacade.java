package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaPostDto;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalShortDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoriaFacade extends BaseFacade<CategoriaGetDto, CategoriaGetDto,  Long> {
    public List<CategoriaGetDto> getCategoriaInsumos ();
    public List<CategoriaGetDto> getCategoriaManufacturados ();
    public void deleteCategoriaInSucursales (Long idCategoria, Long idSucursal);
    public List<CategoriaDto> findCategoriasInsumoBySucursalId(Long idSucursal);
    public List<CategoriaDto> findCategoriasManufacturadoBySucursalId(Long idSucursal);

    public CategoriaGetDto createNew(CategoriaPostDto categoriaDto);

    List<CategoriaGetDto> findAllCategoriasBySucursalId(Long idSucursal);
}
