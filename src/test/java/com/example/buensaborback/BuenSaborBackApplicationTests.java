package com.example.buensaborback;
/*
import com.example.buensaborback.entities.*;
import com.example.buensaborback.entities.dtos.*;
import com.example.buensaborback.mappers.*;
import com.example.buensaborback.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BuenSaborBackApplicationTests {
	// Repositorios
	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;
	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;
	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	//Mappers
	@Autowired
	private IUnidadMedidaMapper unidadMedidaMapper;
	@Autowired
	private ICategoriaMapper categoriaMapper;
	@Autowired
	private IArticuloManufacturadoMapper articuloManufacturadoMapper;
	@Autowired
	private IArticuloInsumoMapper articuloInsumoMapper;
	@Autowired
	private IArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper;

	@Test
	void TestUnidadMedida() {
		List<UnidadMedida> unidadMedidaList = unidadMedidaRepository.findAll();
		System.out.println("Unidad de medida: " + unidadMedidaList);

		List<UnidadMedidaDto> unidadMedidaDtoList = unidadMedidaMapper.toDTOsList(unidadMedidaList);
		System.out.println("toDtoList: " + unidadMedidaDtoList);

		List<UnidadMedida> unidadMedidaList1 = unidadMedidaMapper.toEntitiesList(unidadMedidaDtoList);
		System.out.println("toEntityList" + unidadMedidaList1);
	}

	@Test
	void TestCategoria() {
		List<Categoria> categoriaList = categoriaRepository.findAll();
		System.out.println("Categoria: " + categoriaList);

		List<CategoriaDto> categoriaDtoList = categoriaMapper.toDTOsList(categoriaList);
		System.out.println("toDtoList: " + categoriaDtoList);

		List<Categoria> categoriaList1 = categoriaMapper.toEntitiesList(categoriaDtoList);
		System.out.println("toEntityList" + categoriaList1);
	}

	@Test
	void TestArticuloManufacturado() {
		List<ArticuloManufacturado> articuluManufacturadoList = articuloManufacturadoRepository.findAll();
		System.out.println("Articulo manufacturado: " + articuluManufacturadoList);

		List<ArticuloManufacturadoDto> articuloManufacturadoDtoList = articuloManufacturadoMapper.toDTOsList(articuluManufacturadoList);
		System.out.println("toDtoList: " + articuloManufacturadoDtoList);

		List<ArticuloManufacturado> articuloManufacturadoList1 = articuloManufacturadoMapper.toEntitiesList(articuloManufacturadoDtoList);
		System.out.println("toEntityList" + articuloManufacturadoList1);
	}

	@Test
	void TestArticuloInsumo() {
		List<ArticuloInsumo> articuloInsumoList = articuloInsumoRepository.findAll();
		System.out.println("Categoria: " + articuloInsumoList);

		List<ArticuloInsumoDto> articuloInsumoDtoList = articuloInsumoMapper.toDTOsList(articuloInsumoList);
		System.out.println("toDtoList: " + articuloInsumoDtoList);

		List<ArticuloInsumo> articuloInsumoList1 = articuloInsumoMapper.toEntitiesList(articuloInsumoDtoList);
		System.out.println("toEntityList" + articuloInsumoList1);
	}

	@Test
	void TestArticuloManufacturadoDetalle() {
		List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleList = articuloManufacturadoDetalleRepository.findAll();
		System.out.println("Categoria: " + articuloManufacturadoDetalleList);

		List<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalleDtoList = articuloManufacturadoDetalleMapper.toDTOsList(articuloManufacturadoDetalleList);
		System.out.println("toDtoList: " + articuloManufacturadoDetalleDtoList);

		List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleList1 = articuloManufacturadoDetalleMapper.toEntitiesList(articuloManufacturadoDetalleDtoList);
		System.out.println("toEntityList" + articuloManufacturadoDetalleList1);
	}

} */
