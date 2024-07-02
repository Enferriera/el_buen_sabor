package com.example.buensaborback;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.enums.*;
import com.example.buensaborback.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
public class BuenSaborBackApplication {
// Aca tiene que inyectar todos los repositorios
// Es por ello que deben crear el paquete repositorio

	private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborBackApplication.class, args);
		logger.info("BACK-DASHBOARD-EL-BUEN-SABOR INICIADO");
	}

/*
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ImagenPersonaRepository imagenPersonaRepository;
	@Autowired
	private PromocionDetalleRepository promocionDetalleRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioClienteRepository usuarioClienteRepository;
	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private StockInsumoSucursalRepository stockInsumoSucursalRepository;

	@Autowired
	private LocalidadRepository localidadRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private SucursalRepository sucursalRepository;

	@Autowired
	private DomicilioRepository domicilioRepository;

	@Autowired
	private UnidadMedidaRepository unidadMedidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ArticuloInsumoRepository articuloInsumoRepository;

	@Autowired
	private ArticuloManufacturadoRepository articuloManufacturadoRepository;

	@Autowired
	private ImagenArticuloRepository imagenArticuloRepository;

	@Autowired
	private PromocionRepository promocionRepository;

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;
	@Autowired
	private PedidoRepository pedidoRepository;


	@Bean
	@Transactional
	CommandLineRunner init(ClienteRepository clienteRepository,
						   ImagenPersonaRepository imagenPersonaRepository,

						   PromocionDetalleRepository promocionDetalleRepository,
						   UsuarioRepository usuarioRepository,
						   UsuarioClienteRepository usuarioClienteRepository,
						   PaisRepository paisRepository,
						   ProvinciaRepository provinciaRepository,
						   LocalidadRepository localidadRepository,
						   EmpresaRepository empresaRepository,
						   SucursalRepository sucursalRepository,
						   DomicilioRepository domicilioRepository,
						   UnidadMedidaRepository unidadMedidaRepository,
						   CategoriaRepository categoriaRepository,
						   ArticuloInsumoRepository articuloInsumoRepository,
						   ArticuloManufacturadoRepository articuloManufacturadoRepository,
						   ImagenArticuloRepository imagenArticuloRepository,
						   PromocionRepository promocionRepository,
						   PedidoRepository pedidoRepository,
						   EmpleadoRepository empleadoRepository, FacturaRepository facturaRepository,
						   StockInsumoSucursalRepository stockInsumoSucursalRepository) {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			Pais pais1 = Pais.builder().nombre("Argentina").build();
			paisRepository.save(pais1);
			//CREACION DE PROVINCIAS
			Provincia provincia1 = Provincia.builder().nombre("Mendoza").pais(pais1).build();
			Provincia provincia2 = Provincia.builder().nombre("Buenos Aires").pais(pais1).build();
			provinciaRepository.save(provincia1);
			provinciaRepository.save(provincia2);

			//CREACION DE LOCALIDADES
			Localidad localidad1 = Localidad.builder().nombre("Lujan de Cuyo").provincia(provincia1).build();
			Localidad localidad2 = Localidad.builder().nombre("Guaymallen").provincia(provincia1).build();
			Localidad localidad3 = Localidad.builder().nombre("Mar del Plata").provincia(provincia2).build();
			Localidad localidad4 = Localidad.builder().nombre("Mar de las Pampas").provincia(provincia2).build();

			localidadRepository.save(localidad1);
			localidadRepository.save(localidad2);
			localidadRepository.save(localidad3);
			localidadRepository.save(localidad4);

			// CREAR EMPRESAS Y SUCURSALES PARA PRUEBAS

			Empresa mama = Empresa.builder().logo("MAMA.jpeg").nombre("LO DE MAMÁ").cuit(30546790L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(mama);

			Sucursal mama1 = Sucursal.builder()
					.nombre("Lo de Mamá - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.logo("MAMA.jpeg")
					.build();

			Domicilio domMama1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(280).piso(0).nroDpto(0).
					localidad(localidad2).build();

			mama1.setDomicilio(domMama1);
			mama1.setEmpresa(mama);

			Sucursal mama2 = Sucursal.builder()
					.nombre("Lo de Mamá - Luján")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(false)
					.logo("MAMA.jpeg")
					.build();

			Domicilio domMama2 = Domicilio.builder().cp(5509).calle("San Martín").numero(1250).piso(0).nroDpto(0).
					localidad(localidad2).build();

			mama2.setDomicilio(domMama2);
			mama2.setEmpresa(mama);

			sucursalRepository.save(mama1);
			sucursalRepository.save(mama2);
			mama.getSucursales().add(mama1);
			mama.getSucursales().add(mama2);
			empresaRepository.save(mama);

			Empresa massa = Empresa.builder().logo("MASSA.jpeg").nombre("LA MASSA").cuit(12345678L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(massa);

			Sucursal massa1 = Sucursal.builder()
					.nombre("La Massa - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.logo("MASSA.jpeg")
					.build();

			Domicilio domMassa1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(762).piso(0).nroDpto(0).
					localidad(localidad2).build();

			massa1.setDomicilio(domMassa1);
			massa1.setEmpresa(massa);

			Sucursal massa2 = Sucursal.builder()
					.nombre("La Massa - Luján")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(false)
					.logo("MASSA.jpeg")
					.build();

			Domicilio domMassa2 = Domicilio.builder().cp(5509).calle("San Martín").numero(1236).piso(0).nroDpto(0).
					localidad(localidad2).build();

			massa2.setDomicilio(domMassa2);
			massa2.setEmpresa(massa);

			sucursalRepository.save(massa1);
			sucursalRepository.save(massa2);
			massa.getSucursales().add(massa1);
			massa.getSucursales().add(massa2);
			empresaRepository.save(massa);

			Empresa mansa = Empresa.builder().logo("MANSA.jpeg").nombre("MANSA BURGER").cuit(75624357L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(mansa);

			Sucursal mansa1 = Sucursal.builder()
					.nombre("Mansa Burger - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.logo("MANSA.jpeg")
					.build();

			Domicilio domMansa1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(894).piso(0).nroDpto(0).
					localidad(localidad2).build();

			mansa1.setDomicilio(domMansa1);
			mansa1.setEmpresa(mansa);

			Sucursal mansa2 = Sucursal.builder()
					.nombre("Mansa Burger - Luján")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(false)
					.logo("MANSA.jpeg")
					.build();

			Domicilio domMansa2 = Domicilio.builder().cp(5509).calle("San Martín").numero(1240).piso(0).nroDpto(0).
					localidad(localidad2).build();

			mansa2.setDomicilio(domMansa2);
			mansa2.setEmpresa(mansa);

			sucursalRepository.save(mansa1);
			sucursalRepository.save(mansa2);
			mansa.getSucursales().add(mansa1);
			mansa.getSucursales().add(mansa2);
			empresaRepository.save(mansa);

			Empresa cacerito = Empresa.builder().logo("CACERITO.jpeg").nombre("BIEN CACERITO").cuit(56789423L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(cacerito);

			Sucursal cacerito1 = Sucursal.builder()
					.nombre("Bien Cacerito - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.logo("CACERITO.jpeg")
					.build();

			Domicilio domCacerito1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(763).piso(0).nroDpto(0).
					localidad(localidad2).build();

			cacerito1.setDomicilio(domCacerito1);
			cacerito1.setEmpresa(cacerito);

			Sucursal cacerito2 = Sucursal.builder()
					.nombre("Bien Cacerito - Luján")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(false)
					.logo("CACERITO.jpeg")
					.build();

			Domicilio domCacerito2 = Domicilio.builder().cp(5509).calle("San Martín").numero(1300).piso(1).nroDpto(5).
					localidad(localidad2).build();

			cacerito2.setDomicilio(domCacerito2);
			cacerito2.setEmpresa(cacerito);

			sucursalRepository.save(cacerito1);
			sucursalRepository.save(cacerito2);
			cacerito.getSucursales().add(cacerito1);
			cacerito.getSucursales().add(cacerito2);
			empresaRepository.save(cacerito);

			Empresa aromas = Empresa.builder().logo("AROMAS.jpeg").nombre("AROMAS").cuit(30546780L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(aromas);

			Sucursal aromas1 = Sucursal.builder()
					.nombre("Aromas - Guaymallén")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(true)
					.logo("AROMAS.jpeg")
					.build();

			Domicilio domAromas1 = Domicilio.builder().cp(5519).calle("Elpidio González").numero(764).piso(5).nroDpto(0).
					localidad(localidad2).build();

			aromas1.setDomicilio(domAromas1);
			aromas1.setEmpresa(aromas);

			Sucursal aromas2 = Sucursal.builder()
					.nombre("Aromas - Luján")
					.horarioApertura(LocalTime.of(20, 0))
					.horarioCierre(LocalTime.of(0, 0))
					.esCasaMatriz(false)
					.logo("AROMAS.jpeg")
					.build();

			Domicilio domAromas2 = Domicilio.builder().cp(5509).calle("San Martín").numero(1008).piso(0).nroDpto(0).
					localidad(localidad2).build();

			aromas2.setDomicilio(domAromas2);
			aromas2.setEmpresa(aromas);

			sucursalRepository.save(aromas1);
			sucursalRepository.save(aromas2);
			aromas.getSucursales().add(aromas1);
			aromas.getSucursales().add(aromas2);
			empresaRepository.save(aromas);

			// Crear Categorías de productos y subCategorías de los mismos
			Categoria categoriaBebidas = Categoria.builder().denominacion("Bebidas").esInsumo(true).
					build();
			categoriaRepository.save(categoriaBebidas);

			Categoria categoriaGaseosas = Categoria.builder().denominacion("Gaseosas").categoriaPadre(categoriaBebidas).esInsumo(true)
					.build();
			categoriaRepository.save(categoriaGaseosas);

			Categoria categoriaTragos = Categoria.builder().denominacion("Con Alcohol").categoriaPadre(categoriaBebidas).esInsumo(true).
					build();
			categoriaRepository.save(categoriaTragos);

			Categoria categoriaPizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).
					build();
			categoriaRepository.save(categoriaPizzas);

			Categoria categoriaHamburguesas = Categoria.builder().denominacion("Hamburguesas").esInsumo(false).
					build();
			categoriaRepository.save(categoriaHamburguesas);

			Categoria categoriaEmpanadas = Categoria.builder().denominacion("Empanadas").esInsumo(false).
					build();
			categoriaRepository.save(categoriaEmpanadas);

			Categoria categoriaCafe = Categoria.builder().denominacion("Café").esInsumo(false).
					build();
			categoriaRepository.save(categoriaCafe);

			Categoria categoriaPasteleria = Categoria.builder().denominacion("Pasteleria").esInsumo(false).
					build();
			categoriaRepository.save(categoriaPasteleria);

			Categoria categoriaInsumos = Categoria.builder().denominacion("Insumos").esInsumo(true).
					build();
			categoriaRepository.save(categoriaInsumos);

			// SUBCATEGORIAS
			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);
			categoriaBebidas.getSubCategorias().add(categoriaCafe);

			// Grabo las Subcategorías
			categoriaRepository.save(categoriaBebidas);

			mama1.getCategorias().add(categoriaGaseosas);
			mama1.getCategorias().add(categoriaBebidas);
			mama1.getCategorias().add(categoriaEmpanadas);
			mama1.getCategorias().add(categoriaInsumos);


//			mama2.getCategorias().add(categoriaGaseosas);
//			mama2.getCategorias().add(categoriaBebidas);
//			mama2.getCategorias().add(categoriaEmpanadas);
//			sucursalRepository.save(mama2);

			mama1.getCategorias().add(categoriaPizzas);
			mama1.getCategorias().add(categoriaTragos);
			//massa1.getCategorias().add(categoriaGaseosas);
			//massa1.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(massa1);

			//massa2.getCategorias().add(categoriaPizzas);
			//massa2.getCategorias().add(categoriaTragos);
			//massa2.getCategorias().add(categoriaGaseosas);
			//massa2.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(massa2);

			mama1.getCategorias().add(categoriaHamburguesas);
			//mansa1.getCategorias().add(categoriaTragos);
			//mansa1.getCategorias().add(categoriaGaseosas);
			//massa1.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(mansa1);

			//mansa2.getCategorias().add(categoriaHamburguesas);
			//mansa2.getCategorias().add(categoriaTragos);
			//mansa2.getCategorias().add(categoriaGaseosas);
			//assa2.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(mansa2);

			//cacerito1.getCategorias().add(categoriaGaseosas);
			//cacerito1.getCategorias().add(categoriaBebidas);
			//cacerito1.getCategorias().add(categoriaEmpanadas);
			sucursalRepository.save(cacerito1);

			//cacerito2.getCategorias().add(categoriaGaseosas);
			//cacerito2.getCategorias().add(categoriaBebidas);
			//cacerito2.getCategorias().add(categoriaEmpanadas);
			sucursalRepository.save(cacerito2);

			mama1.getCategorias().add(categoriaCafe);
			mama1.getCategorias().add(categoriaPasteleria);
			sucursalRepository.save(aromas1);

			//aromas2.getCategorias().add(categoriaCafe);
			//aromas2.getCategorias().add(categoriaPasteleria);
			sucursalRepository.save(aromas2);

			sucursalRepository.save(mama1);
			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().denominacion("Unidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);


			// Crear Insumos , coca cola , harina , etc

			ArticuloInsumo cerveza = ArticuloInsumo.builder().
					denominacion("Cerveza").
					codigo("I102").
					unidadMedida(unidadMedidaLitros).
					esParaElaborar(false).
					precioCompra(70.0).
					precioVenta(140.0).
					habilitado(true).
					build();

			ArticuloInsumo cocaCola = ArticuloInsumo.builder().
					denominacion("Coca cola").
					codigo("I101").
					unidadMedida(unidadMedidaLitros).
					esParaElaborar(false).
					precioCompra(50.0).
					precioVenta(70.0).
					habilitado(true).
					build();
			ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").codigo("I001").unidadMedida(unidadMedidaGramos).esParaElaborar(true).precioCompra(40.0).precioVenta(60.5).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().denominacion("Queso").codigo("I002").unidadMedida(unidadMedidaGramos).esParaElaborar(true).precioCompra(23.6).precioVenta(66.6).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().denominacion("Tomate").codigo("I003").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).precioCompra(23.6).precioVenta(66.6).build();
			ImagenArticulo imagenArticuloCoca = ImagenArticulo.builder().
					url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").
					build();
			ImagenArticulo imagenArticuloCerveza = ImagenArticulo.builder().
					url("https://masonlineprod.vtexassets.com/arquivos/ids/272997-800-auto?v=638116617849200000&width=800&height=auto&aspect=true").
					build();
			ImagenArticulo imagenArticuloHarina = ImagenArticulo.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
			ImagenArticulo imagenArticuloQueso = ImagenArticulo.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
			ImagenArticulo imagenArticuloTomate = ImagenArticulo.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();
			imagenArticuloRepository.save(imagenArticuloCoca);
			imagenArticuloRepository.save(imagenArticuloHarina);
			imagenArticuloRepository.save(imagenArticuloQueso);
			imagenArticuloRepository.save(imagenArticuloTomate);
			imagenArticuloRepository.save(imagenArticuloCerveza);

			//ASOCIAMOS IMAGEN CON INSUMOS
			cocaCola.getImagenes().add(imagenArticuloCoca);
			harina.getImagenes().add(imagenArticuloHarina);
			queso.getImagenes().add(imagenArticuloQueso);
			tomate.getImagenes().add(imagenArticuloTomate);
			cerveza.getImagenes().add(imagenArticuloCerveza);

			// Grabamos los Articulos
			StockInsumoSucursal stockCocacolaSuc1=StockInsumoSucursal.builder().
					stockActual(15).
					stockMinimo(10).
					stockMaximo(50).
					articuloInsumo(cocaCola).sucursal(mama1).build();
			StockInsumoSucursal stockHarinaSuc1=StockInsumoSucursal.builder().stockActual(4000).stockMinimo(500).stockMaximo(4000).articuloInsumo(harina).sucursal(mama1).build();
			StockInsumoSucursal stockQuesoSuc1=StockInsumoSucursal.builder().stockActual(3000).stockMinimo(1000).stockMaximo(4000).articuloInsumo(queso).sucursal(mama1).build();
			StockInsumoSucursal stockTomateSuc1=StockInsumoSucursal.builder().stockActual(30).stockMinimo(10).stockMaximo(40).articuloInsumo(tomate).sucursal(mama1).build();
			StockInsumoSucursal stockCervezaSuc1=StockInsumoSucursal.builder().stockActual(30).stockMinimo(10).stockMaximo(70).articuloInsumo(cerveza).sucursal(mama1).build();

			harina.getStocksInsumo().add(stockHarinaSuc1);
			cocaCola.getStocksInsumo().add(stockCocacolaSuc1);
			queso.getStocksInsumo().add(stockQuesoSuc1);
			tomate.getStocksInsumo().add(stockTomateSuc1);
			cerveza.getStocksInsumo().add(stockCervezaSuc1);

			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);
			articuloInsumoRepository.save(cerveza);


			//ASOCIAMOS CATEGORIA CON INSUMOS
			categoriaInsumos.getArticulos().add(harina);
			categoriaInsumos.getArticulos().add(queso);
			categoriaInsumos.getArticulos().add(tomate);
			categoriaGaseosas.getArticulos().add(cocaCola);
			categoriaTragos.getArticulos().add(cerveza);
			categoriaRepository.save(categoriaTragos);
			categoriaRepository.save(categoriaInsumos);
			categoriaRepository.save(categoriaGaseosas);

			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().
					denominacion("Pizza Muzarella").
					codigo("M001").
					descripcion("Una pizza clasica").
					unidadMedida(unidadMedidaPorciones).
					precioVenta(130.0).
					tiempoEstimadoMinutos(15).
					preparacion("Pasos de preparacion de una muzza de toda la vida").
					habilitado(false).
					build();
			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().denominacion("Pizza Napolitana").codigo("M002").descripcion("Una pizza napolitana").unidadMedida(unidadMedidaPorciones).precioVenta(150.0).tiempoEstimadoMinutos(15).preparacion("Pasos de preparacion de una pizza napolitana italiana").habilitado(true).build();

			// Crear fotos para los artículos manufacturados
			ImagenArticulo imagenArticuloPizzaMuzarella = ImagenArticulo.builder().
					url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").
					build();
			ImagenArticulo imagenArticuloPizzaNapolitana = ImagenArticulo.builder().url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").build();
			imagenArticuloRepository.save(imagenArticuloPizzaMuzarella);
			imagenArticuloRepository.save(imagenArticuloPizzaNapolitana);

			//ASOCIAMOS IMAGEN CON ARTICULO MANUFACTURADO
			pizzaMuzarella.getImagenes().add(imagenArticuloPizzaMuzarella);
			pizzaNapolitana.getImagenes().add(imagenArticuloPizzaNapolitana);
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Establecer las relaciones entre estos objetos. Art+iculos de la Receta independiente
			ArticuloManufacturadoDetalle detalle1 = ArticuloManufacturadoDetalle.builder().
					articuloInsumo(harina).
					cantidad(300).
					build();
			ArticuloManufacturadoDetalle detalle2 = ArticuloManufacturadoDetalle.builder().articuloInsumo(queso).cantidad(600).build();
			ArticuloManufacturadoDetalle detalle3 = ArticuloManufacturadoDetalle.builder().articuloInsumo(harina).cantidad(350).build();
			ArticuloManufacturadoDetalle detalle4 = ArticuloManufacturadoDetalle.builder().articuloInsumo(queso).cantidad(650).build();
			ArticuloManufacturadoDetalle detalle5 = ArticuloManufacturadoDetalle.builder().articuloInsumo(tomate).cantidad(2).build();
			// grabamos el Artículo Manufacturado
			articuloManufacturadoDetalleRepository.save(detalle1);
			articuloManufacturadoDetalleRepository.save(detalle2);
			articuloManufacturadoDetalleRepository.save(detalle3);
			articuloManufacturadoDetalleRepository.save(detalle4);
			articuloManufacturadoDetalleRepository.save(detalle5);

			//ASOCIAMOS LOS DETALLE MANUFACTURADO AL ARTICULO MANUFACTURADO - LA RECETA
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle1);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle2);

			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle3);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle4);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle5);
			// GRABAMOS LA RECETA
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Establecer relaciones de las categorias - Cada Producto Manufacturado Pertenece a una categoria

			categoriaPizzas.getArticulos().add(pizzaMuzarella);
			categoriaPizzas.getArticulos().add(pizzaNapolitana);
			// Graba la categoria y los productos asociados
			categoriaRepository.save(categoriaPizzas);

			//HAMBURGUESAS
			ArticuloManufacturado hamburguesaSimple = ArticuloManufacturado.builder()
					.denominacion("Hamburguesa Simple")
					.codigo("M003")
					.descripcion("Un jugoso medallón de carne perfectamente sazonado y un generoso trozo de queso derretido se unen en un abrazo armonioso sobre un pan suave.")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(8000.0)
					.tiempoEstimadoMinutos(30)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ArticuloManufacturado hamburguesaCompleta = ArticuloManufacturado.builder()
					.denominacion("Hamburguesa Completa")
					.codigo("M004")
					.descripcion("Esta hamburguesa trae un medallón de carne y queso derretido. Agregamos una explosión de frescura con crujiente lechuga, jugosas rodajas de tomate y un huevo frito que aporta un toque cremoso.")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(9000.0)
					.tiempoEstimadoMinutos(30)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ArticuloManufacturado hamburguesaBacon = ArticuloManufacturado.builder()
					.denominacion("Hamburguesa con Bacon")
					.codigo("M005")
					.descripcion("Un medallón de carne jugoso se combina con la irresistible textura crujiente del bacon y el toque de la salsa BBQ. Un toque final de queso cheddar derretido corona esta creación culinaria, creando una explosión de sabores que te dejarán sin aliento.")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(9000.0)
					.tiempoEstimadoMinutos(30)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ImagenArticulo hamSimple = ImagenArticulo.builder()
					.url("https://deliexpress.com/wp-content/uploads/2021/05/0021_HTG-LU-Cheeseburger-1.jpg")
					.build();

			ImagenArticulo hamCompleta = ImagenArticulo.builder()
					.url("https://cdn.pixabay.com/photo/2024/04/26/05/52/cheeseburger-8721189_1280.png")
					.build();

			ImagenArticulo hamBacon = ImagenArticulo.builder()
					.url("https://cdn.pixabay.com/photo/2022/11/09/13/58/cheeseburger-7580676_1280.jpg")
					.build();

			hamburguesaSimple.getImagenes().add(hamSimple);
			hamburguesaCompleta.getImagenes().add(hamCompleta);
			hamburguesaBacon.getImagenes().add(hamBacon);

			imagenArticuloRepository.save(hamSimple);
			imagenArticuloRepository.save(hamCompleta);
			imagenArticuloRepository.save(hamBacon);

			articuloManufacturadoRepository.save(hamburguesaSimple);
			articuloManufacturadoRepository.save(hamburguesaCompleta);
			articuloManufacturadoRepository.save(hamburguesaBacon);

			categoriaHamburguesas.getArticulos().add(hamburguesaSimple);
			categoriaHamburguesas.getArticulos().add(hamburguesaCompleta);
			categoriaHamburguesas.getArticulos().add(hamburguesaBacon);

			categoriaRepository.save(categoriaHamburguesas);

			//EMPANADAS
			ArticuloManufacturado empanadaCarne = ArticuloManufacturado.builder()
					.denominacion("Empanada de Carne")
					.codigo("M006")
					.descripcion("Un clásico argentino que nunca falla. Un sabroso relleno de carne molida sazonada a la perfección con especias tradicionales, envuelto en una masa crujiente y dorada. ")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(800.0)
					.tiempoEstimadoMinutos(15)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ArticuloManufacturado empanadaJyQ = ArticuloManufacturado.builder()
					.denominacion("Empanada de Jamón y Queso")
					.codigo("M007")
					.descripcion("La combinación perfecta de simplicidad y sabor. Jamón de alta calidad y queso derretido se unen en un abrazo cremoso dentro de una masa suave y mantecosa. Una opción deliciosa para quienes buscan un sabor familiar y reconfortante.")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(900.0)
					.tiempoEstimadoMinutos(15)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ArticuloManufacturado empanadaHumita = ArticuloManufacturado.builder()
					.denominacion("Empanada de humita")
					.codigo("M008")
					.descripcion("La cremosa humita, a base de maíz fresco y queso, se encierra en una masa artesanal para crear una explosión de sabor en cada bocado. Una experiencia culinaria que te sorprenderá y deleitará.")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(750.0)
					.tiempoEstimadoMinutos(15)
					.unidadMedida(unidadMedidaCantidad)
					.build();

			ImagenArticulo empCarne = ImagenArticulo.builder()
					.url("https://saborargento.com.ar/wp-content/uploads/2023/06/Receta-de-Empanadas-de-Carne.jpg")
					.build();

			ImagenArticulo empJyQ = ImagenArticulo.builder()
					.url("https://distrifood.com.ar/wp-content/uploads/2023/06/img-empanadas-jamon-queso.png")
					.build();

			ImagenArticulo empHumita = ImagenArticulo.builder()
					.url("https://distrifood.com.ar/wp-content/uploads/2023/06/6103371.jpg")
					.build();

			empanadaCarne.getImagenes().add(empCarne);
			empanadaJyQ.getImagenes().add(empJyQ);
			empanadaHumita.getImagenes().add(empHumita);

			imagenArticuloRepository.save(empCarne);
			imagenArticuloRepository.save(empJyQ);
			imagenArticuloRepository.save(empHumita);

			articuloManufacturadoRepository.save(empanadaCarne);
			articuloManufacturadoRepository.save(empanadaJyQ);
			articuloManufacturadoRepository.save(empanadaHumita);

			categoriaEmpanadas.getArticulos().add(empanadaCarne);
			categoriaEmpanadas.getArticulos().add(empanadaJyQ);
			categoriaEmpanadas.getArticulos().add(empanadaHumita);

			categoriaRepository.save(categoriaEmpanadas);

			//PASTELERIA
			ArticuloManufacturado lemonPie = ArticuloManufacturado.builder()
					.denominacion("Lemon Pie")
					.codigo("M009")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(2000.0)
					.descripcion("Crema de limón sobre una masa crujiente y dorada, coronada con un merengue suave y esponjoso. Un postre ácido y dulce que te conquistará desde el primer bocado.")
					.unidadMedida(unidadMedidaPorciones)
					.tiempoEstimadoMinutos(15)
					.build();

			ArticuloManufacturado cheesecake = ArticuloManufacturado.builder()
					.denominacion("Cheesecake")
					.codigo("M010")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(2000.0)
					.descripcion("Un postre irresistiblemente cremoso y suave. Queso crema de alta calidad se mezcla con vainilla y azúcar para rellenar una base de galleta crujiente.")
					.unidadMedida(unidadMedidaPorciones)
					.tiempoEstimadoMinutos(15)
					.build();

			ImagenArticulo imgLemonPie = ImagenArticulo.builder()
					.url("https://laopinionaustral.com.ar/media/uploads/2023/08/lemon-pie-torta.webp")
					.build();

			ImagenArticulo imgCheesecake = ImagenArticulo.builder()
					.url("https://cdn.pixabay.com/photo/2023/03/22/11/08/ai-generated-7869200_1280.jpg")
					.build();

			lemonPie.getImagenes().add(imgLemonPie);
			cheesecake.getImagenes().add(imgCheesecake);

			imagenArticuloRepository.save(imgCheesecake);
			imagenArticuloRepository.save(imgLemonPie);

			articuloManufacturadoRepository.save(lemonPie);
			articuloManufacturadoRepository.save(cheesecake);

			categoriaPasteleria.getArticulos().add(lemonPie);
			categoriaPasteleria.getArticulos().add(cheesecake);

			categoriaRepository.save(categoriaPasteleria);

			//CAFE
			ArticuloManufacturado cafeSimple = ArticuloManufacturado.builder()
					.denominacion("Cafe Mediano")
					.codigo("M011")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(1000.0)
					.descripcion("")
					.unidadMedida(unidadMedidaPorciones)
					.tiempoEstimadoMinutos(15)
					.build();

			ArticuloManufacturado caramelMacchiato = ArticuloManufacturado.builder()
					.denominacion("Caramel Macchiato")
					.codigo("M012")
					.eliminado(false)
					.habilitado(true)
					.precioVenta(1700.0)
					.descripcion("")
					.unidadMedida(unidadMedidaPorciones)
					.tiempoEstimadoMinutos(15)
					.build();

			ImagenArticulo imgCafeSimple = ImagenArticulo.builder()
					.url("https://cdn.pixabay.com/photo/2015/05/07/13/46/cappuccino-756490_1280.jpg")
					.build();

			ImagenArticulo imgMacchiato = ImagenArticulo.builder()
					.url("https://cdn.pixabay.com/photo/2014/01/15/09/53/latte-macchiato-245477_1280.jpg")
					.build();

			cafeSimple.getImagenes().add(imgCafeSimple);
			caramelMacchiato.getImagenes().add(imgMacchiato);

			imagenArticuloRepository.save(imgCafeSimple);
			imagenArticuloRepository.save(imgMacchiato);

			articuloManufacturadoRepository.save(cafeSimple);
			articuloManufacturadoRepository.save(caramelMacchiato);

			categoriaCafe.getArticulos().add(cafeSimple);
			categoriaCafe.getArticulos().add(caramelMacchiato);

			categoriaRepository.save(categoriaCafe);

			// Crear promocion para sucursal
			// Tener en cuenta que esa promocion es exclusivamente para una sucursal determinada d euna empresa determinada
			Promocion promocionDiaEnamorados = Promocion.builder().denominacion("Dia de los Enamorados")
					.fechaDesde(LocalDate.of(2024, 2, 13))
					.fechaHasta(LocalDate.of(2024, 2, 15))
					.horaDesde(LocalTime.of(0, 0))
					.horaHasta(LocalTime.of(23, 59))
					.descripcionDescuento("El descuento que se hace por san valentin, un dia antes y un dia despues")
					.precioPromocional(100.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();
			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo1 = PromocionDetalle.builder().cantidad(2).articulo(pizzaNapolitana).build();

			PromocionDetalle detallePromo2 = PromocionDetalle.builder().cantidad(1).articulo(cocaCola).build();

			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo1);
			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo2);

			promocionRepository.save(promocionDiaEnamorados);

			Promocion pizzaConCoca = Promocion.builder().denominacion("Piza + coca")
					.fechaDesde(LocalDate.of(2024, 2, 13))
					.fechaHasta(LocalDate.of(2024, 2, 15))
					.horaDesde(LocalTime.of(0, 0))
					.horaHasta(LocalTime.of(23, 59))
					.descripcionDescuento("Pizza + Coca Cola 1.5lts")
					.precioPromocional(100.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();
			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo3 = PromocionDetalle.builder().cantidad(1).articulo(pizzaNapolitana).build();

			PromocionDetalle detallePromo4 = PromocionDetalle.builder().cantidad(2).articulo(cocaCola).build();
			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo3);
			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo4);

			promocionRepository.save(pizzaConCoca);

			// revisar PARA QUE GRABE EL DETALLE D ELA PROMOCION
			//-------------- ACA HAY QUE HARCODEAR PARA TRAER POR ID CADA SUCURSAL
			// La sucursal buscada, luego debe salvarse nuevamente, pero ahora ya existe es como un Updete
			// Peimero la busco y luego la grabo

			//sucursalRepository.findById();
			//--------------------- ESTOS SAVE SE HACIAN NUEVAMENTE CON LA INSTANCIA ANTERIOR
			//  Por eso daba duplicado, revisa rla logica de esta parte
			// Sucursal Guaymallee
			Sucursal sucursalId1 = sucursalRepository.findWithPromocionesById(1L);
			Sucursal sucursalId2 = sucursalRepository.findWithPromocionesById(2L);
			Promocion promocionId1 = promocionRepository.findAllWithSucursales(1L);
			Promocion promocionId2 = promocionRepository.findAllWithSucursales(2L);
			sucursalId1.getPromociones().add(promocionId1);
			sucursalId1.getPromociones().add(promocionId2);
			promocionId1.getSucursales().add(sucursalId1);
			promocionId1.getSucursales().add(sucursalId2);
			sucursalRepository.save(sucursalId1);
			sucursalRepository.save(sucursalId2);
			promocionRepository.save(promocionId1);
			promocionRepository.save(promocionId2);
			logger.info("---------------Promociones en sucursal id = 1---------------");
			sucursalId1.getPromociones()
					.stream()
					.map(Promocion::getDenominacion)
					.forEach(logger::info);
			logger.info("---------------Sucursales con la promocion id = 1---------------");
			promocionId1.getSucursales()
					.stream()
					.map(Sucursal::getNombre)
					.forEach(logger::info);

			logger.info("----------------------------------------------------------------");

			// Crear promoción para una sucursal específica
			Promocion promocionDiaIndependencia = Promocion.builder().denominacion("Día de la Independencia")
					.fechaDesde(LocalDate.of(2024, 7, 8))
					.fechaHasta(LocalDate.of(2024, 7, 10))
					.horaDesde(LocalTime.of(0, 0))
					.horaHasta(LocalTime.of(23, 59))
					.descripcionDescuento("Descuento especial por el Día de la Independencia, un día antes y un día después")
					.precioPromocional(120.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();

			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo5 = PromocionDetalle.builder().cantidad(2).articulo(empanadaCarne).build();
			PromocionDetalle detallePromo6 = PromocionDetalle.builder().cantidad(1).articulo(cerveza).build();

			promocionDiaIndependencia.getPromocionDetalles().add(detallePromo5);
			promocionDiaIndependencia.getPromocionDetalles().add(detallePromo6);

			promocionRepository.save(promocionDiaIndependencia);

			Promocion empanadaCerveza = Promocion.builder().denominacion("Empanada + Cerveza")
					.fechaDesde(LocalDate.of(2024, 7, 8))
					.fechaHasta(LocalDate.of(2024, 7, 10))
					.horaDesde(LocalTime.of(0, 0))
					.horaHasta(LocalTime.of(23, 59))
					.descripcionDescuento("Hamburguesa + Pepsi 1.5lts")
					.precioPromocional(120.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();

			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo7 = PromocionDetalle.builder().cantidad(1).articulo(empanadaCarne).build();
			PromocionDetalle detallePromo8 = PromocionDetalle.builder().cantidad(2).articulo(cerveza).build();

			empanadaCerveza.getPromocionDetalles().add(detallePromo7);
			empanadaCerveza.getPromocionDetalles().add(detallePromo8);

			promocionRepository.save(empanadaCerveza);

			// Revisar para que grabe el detalle de la promoción
			// Harcodear para traer por ID cada sucursal
			sucursalId1 = sucursalRepository.findWithPromocionesById(3L);
			sucursalId2 = sucursalRepository.findWithPromocionesById(4L);
			Promocion promocionId3 = promocionRepository.findAllWithSucursales(3L);
			Promocion promocionId4 = promocionRepository.findAllWithSucursales(4L);

			sucursalId1.getPromociones().add(promocionId3);
			sucursalId1.getPromociones().add(promocionId4);
			promocionId3.getSucursales().add(sucursalId1);
			promocionId3.getSucursales().add(sucursalId2);

			sucursalRepository.save(sucursalId1);
			sucursalRepository.save(sucursalId2);
			promocionRepository.save(promocionId3);
			promocionRepository.save(promocionId4);

			logger.info("---------------Promociones en sucursal id = 3---------------");
			sucursalId1.getPromociones()
					.stream()
					.map(Promocion::getDenominacion)
					.forEach(logger::info);

			logger.info("---------------Sucursales con la promoción id = 3---------------");
			promocionId3.getSucursales()
					.stream()
					.map(Sucursal::getNombre)
					.forEach(logger::info);

			logger.info("----------------------------------------------------------------");

			//Crea un Empleado
			Usuario usuario1 = Usuario.builder()
					.auth0Id("auth0|667b55e1bf00228ffd3339d8")
					.username("juanUsuario")
					.email("juan@aaa.com")
					.rol(Rol.COCINERO).build();
			Empleado juan = Empleado.builder()
					.usuario(usuario1)
					.sucursal(mama1)
					.nombre("Juan")
					.apellido("Vidable")
					.telefono("22222")
					.fechaNacimiento(LocalDate.of(1990, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario2 = Usuario.builder()
					.auth0Id("auth0|667b5631cedfe31b7d424565")
					.username("caroUsuario")
					.email("carp@aaa.com")
					.rol(Rol.CAJERO).build();
			Empleado caro = Empleado.builder()
					.usuario(usuario2)
					.sucursal(mama1)
					.nombre("Caro")
					.apellido("Solsona")
					.telefono("33333")
					.fechaNacimiento(LocalDate.of(1998,1,1))
					.imagenPersona(null)
					.build();

			Usuario usuario3 = Usuario.builder()
					.auth0Id("auth0|667b566a389bcd997059cf6f")
					.username("arielUsuario")
					.email("ariel@aaa.com")
					.rol(Rol.GERENTE).build();
			Empleado ariel = Empleado.builder()
					.usuario(usuario3)
					.sucursal(mama1)
					.nombre("Ariel")
					.apellido("Enferreira")
					.telefono("44444")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario4 = Usuario.builder()
					.auth0Id("auth0|667b569abf00228ffd333a7b")
					.username("cortezUsuario")
					.email("cortez@aaa.com")
					.rol(Rol.DELIVERY).build();
			Empleado cortez = Empleado.builder()
					.usuario(usuario4)
					.sucursal(mama1)
					.nombre("Alberto")
					.apellido("Cortez")
					.telefono("55555")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario5 = Usuario.builder()
					.auth0Id("auth0|667b57935b7b2edf7f883f05")
					.username("magniUsuario")
					.email("magni@aaa.com")
					.rol(Rol.CAJERO).build();
			Empleado magni = Empleado.builder()
					.usuario(usuario5)
					.sucursal(mama2)
					.nombre("Gerardo")
					.apellido("Magni")
					.telefono("66666")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario6 = Usuario.builder()
					.auth0Id("auth0|667b56fdbf00228ffd333ad7")
					.username("sanchezUsuario")
					.email("sanchez@aaa.com")
					.rol(Rol.COCINERO).build();
			Empleado sanchez = Empleado.builder()
					.usuario(usuario6)
					.sucursal(mama2)
					.nombre("Miguel")
					.apellido("Sanchez")
					.telefono("77777")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario7 = Usuario.builder()
					.auth0Id("auth0|667b57ba389bcd997059d089")
					.username("joseUsuario")
					.email("jose@aaa.com")
					.rol(Rol.GERENTE).build();
			Empleado jose = Empleado.builder()
					.usuario(usuario7)
					.sucursal(mama2)
					.nombre("Adriana")
					.apellido("Jose")
					.telefono("88888")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario8 = Usuario.builder()
					.auth0Id("auth0|667b57e55b7b2edf7f883f51")
					.username("yacomoUsuario")
					.email("yacomo@aaa.com")
					.rol(Rol.DELIVERY).build();
			Empleado yacomo = Empleado.builder()
					.usuario(usuario8)
					.sucursal(mama2)
					.nombre("Carlos")
					.apellido("Yacomo")
					.telefono("99999")
					.fechaNacimiento(LocalDate.of(1999, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario9 = Usuario.builder()
					.auth0Id("auth0|667b58345b7b2edf7f883f96")
					.username("admin1Usuario")
					.email("admin1@aaa.com")
					.rol(Rol.ADMIN).build();
			Empleado admin1 = Empleado.builder()
					.usuario(usuario9)
					.sucursal(mama1)
					.nombre("Andres")
					.apellido("Guevara")
					.telefono("11111")
					.fechaNacimiento(LocalDate.of(1994, 1, 1))
					.imagenPersona(null)
					.build();

			Usuario usuario10 = Usuario.builder()
					.auth0Id("auth0|667b56c7aefe511b879e0b71")
					.username("admin2Usuario")
					.email("admin2@aaa.com")
					.rol(Rol.ADMIN).build();
			Empleado admin2 = Empleado.builder()
					.usuario(usuario10)
					.sucursal(mama2)
					.nombre("Jorge")
					.apellido("De la Torre")
					.build();


			empleadoRepository.save(juan);
			empleadoRepository.save(caro);
			empleadoRepository.save(ariel);
			empleadoRepository.save(cortez);
			empleadoRepository.save(magni);
			empleadoRepository.save(sanchez);
			empleadoRepository.save(jose);
			empleadoRepository.save(yacomo);
			empleadoRepository.save(admin1);
			empleadoRepository.save(admin2);

			UsuarioCliente usuarioCliente1 = UsuarioCliente.builder()
					.email("enferrelariel@hotmail.com")
					.build();
			usuarioCliente1.setClaveEncriptada("contraseña");
			usuarioClienteRepository.save(usuarioCliente1);
			Domicilio domicilioCliente1 = Domicilio.builder()
					.calle("Calle 1")
					.numero(123)
					.cp(1111)
					.localidad(localidad1)
					.build();
			domicilioRepository.save(domicilioCliente1);
			Cliente cliente1 = Cliente.builder()
					.nombre("Ariel")
					.apellido("Enferrel")
					.telefono("2615546754")
					.usuarioCliente(usuarioCliente1)
					.build();
			cliente1.getDomicilios().add(domicilioCliente1);
			clienteRepository.save(cliente1);

			UsuarioCliente usuarioCliente2 = UsuarioCliente.builder()
					.email("juanvidable123@gmail.com")
					.build();
			usuarioCliente2.setClaveEncriptada("contraseña2");
			usuarioClienteRepository.save(usuarioCliente2);
			Domicilio domicilioCliente2 = Domicilio.builder()
					.calle("Calle 2")
					.numero(123)
					.cp(1111)
					.localidad(localidad2)
					.build();
			domicilioRepository.save(domicilioCliente2);
			Domicilio domicilioCliente4 = Domicilio.builder()
					.calle("Calle 4")
					.numero(123)
					.cp(1111)
					.localidad(localidad4)
					.build();
			domicilioRepository.save(domicilioCliente4);
			Cliente cliente2 = Cliente.builder()
					.nombre("Juan")
					.apellido("Vidable")
					.telefono("2613313061")
					.usuarioCliente(usuarioCliente2)
					.build();

			cliente2.getDomicilios().add(domicilioCliente2);
			cliente2.getDomicilios().add(domicilioCliente4);
			clienteRepository.save(cliente2);

			UsuarioCliente usuarioCliente3 = UsuarioCliente.builder()
					.email("luanmollo@gmail.com")
					.build();
			usuarioCliente3.setClaveEncriptada("contraseña3");
			usuarioClienteRepository.save(usuarioCliente3);
			Domicilio domicilioCliente3 = Domicilio.builder()
					.calle("Calle 3")
					.numero(123)
					.cp(1111)
					.localidad(localidad2)
					.build();
			domicilioRepository.save(domicilioCliente3);
			Cliente cliente3 = Cliente.builder()
					.nombre("Luan")
					.apellido("Mollo")
					.telefono("261025532")
					.usuarioCliente(usuarioCliente3)
					.build();
			cliente3.getDomicilios().add(domicilioCliente3);
			clienteRepository.save(cliente3);

			//Crea un pedido para el cliente
			Pedido pedido = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estadoPedido(EstadoPedido.COMPLETADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido1.calculaSubtotal();
			DetallePedido detallePedido2 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido2.calculaSubtotal();
			DetallePedido detallePedido3 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido3.calculaSubtotal();
			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);
			pedido.getDetallePedidos().add(detallePedido3);
			pedido.setCliente(cliente3);
			pedidoRepository.save(pedido);

			Pedido pedido2 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(400.0)
					.totalCosto(200.6)
					.estadoPedido(EstadoPedido.EN_CAMINO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente2)
					.build();

			DetallePedido detallePedido4 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(2).build();
			detallePedido4.calculaSubtotal();
			DetallePedido detallePedido5 = DetallePedido.builder().articulo(cocaCola).cantidad(3).build();
			detallePedido5.calculaSubtotal();
			DetallePedido detallePedido6 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido6.calculaSubtotal();
			pedido2.getDetallePedidos().add(detallePedido4);
			pedido2.getDetallePedidos().add(detallePedido5);
			pedido2.getDetallePedidos().add(detallePedido6);
			pedido2.setCliente(cliente2);
			pedidoRepository.save(pedido2);

			Pedido pedido3 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(250.0)
					.totalCosto(80.6)
					.estadoPedido(EstadoPedido.PAGADO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido7 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido7.calculaSubtotal();
			DetallePedido detallePedido8 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido8.calculaSubtotal();
			DetallePedido detallePedido9 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido9.calculaSubtotal();
			pedido3.getDetallePedidos().add(detallePedido7);
			pedido3.getDetallePedidos().add(detallePedido8);
			pedido3.getDetallePedidos().add(detallePedido9);
			pedido3.setCliente(cliente2);
			pedidoRepository.save(pedido3);

			Pedido pedido4 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(600.0)
					.totalCosto(300.6)
					.estadoPedido(EstadoPedido.PENDIENTE_ENTREGA)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido10 = DetallePedido.builder().articulo(empanadaCarne).cantidad(24).build();
			detallePedido10.calculaSubtotal();
			DetallePedido detallePedido11 = DetallePedido.builder().articulo(cerveza).cantidad(4).build();
			detallePedido11.calculaSubtotal();
			pedido4.getDetallePedidos().add(detallePedido10);
			pedido4.getDetallePedidos().add(detallePedido11);
			pedido4.setCliente(cliente3);
			pedidoRepository.save(pedido4);

			Pedido pedido5 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PREPARACION)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido12 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido12.calculaSubtotal();
			DetallePedido detallePedido13 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido13.calculaSubtotal();
			DetallePedido detallePedido14 = DetallePedido.builder().promocion(promocionDiaIndependencia).cantidad(1).build();
			detallePedido14.calculaSubtotal();
			pedido5.getDetallePedidos().add(detallePedido12);
			pedido5.getDetallePedidos().add(detallePedido13);
			pedido5.getDetallePedidos().add(detallePedido14);
			pedido5.setCliente(cliente2);
			pedidoRepository.save(pedido5);

			Pedido pedido6 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PENDIENTE_PAGO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido15 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido15.calculaSubtotal();
			DetallePedido detallePedido16 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido16.calculaSubtotal();
			pedido6.getDetallePedidos().add(detallePedido15);
			pedido6.getDetallePedidos().add(detallePedido16);
			pedido6.setCliente(cliente2);
			pedidoRepository.save(pedido6);

			Pedido pedido7 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.NOTA_CREDITO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente1)
					.build();

			DetallePedido detallePedido17 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido17.calculaSubtotal();
			DetallePedido detallePedido18 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido18.calculaSubtotal();
			pedido7.getDetallePedidos().add(detallePedido17);
			pedido7.getDetallePedidos().add(detallePedido18);
			pedido7.setCliente(cliente1);
			pedidoRepository.save(pedido7);

			Pedido pedido8 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.CANCELADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido19 = DetallePedido.builder().articulo(cafeSimple).cantidad(2).build();
			detallePedido19.calculaSubtotal();
			DetallePedido detallePedido20 = DetallePedido.builder().articulo(cheesecake).cantidad(4).build();
			detallePedido20.calculaSubtotal();
			pedido8.getDetallePedidos().add(detallePedido19);
			pedido8.getDetallePedidos().add(detallePedido20);
			pedido8.setCliente(cliente2);
			pedidoRepository.save(pedido8);




			Pedido pedido9 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estadoPedido(EstadoPedido.COMPLETADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido21 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido21.calculaSubtotal();
			DetallePedido detallePedido22 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido22.calculaSubtotal();
			DetallePedido detallePedido23 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido23.calculaSubtotal();
			pedido9.getDetallePedidos().add(detallePedido21);
			pedido9.getDetallePedidos().add(detallePedido22);
			pedido9.getDetallePedidos().add(detallePedido23);
			pedido9.setCliente(cliente3);
			pedidoRepository.save(pedido9);

			Pedido pedido10 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(400.0)
					.totalCosto(200.6)
					.estadoPedido(EstadoPedido.EN_CAMINO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente2)
					.build();

			DetallePedido detallePedido24 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(2).build();
			detallePedido24.calculaSubtotal();
			DetallePedido detallePedido25 = DetallePedido.builder().articulo(cocaCola).cantidad(3).build();
			detallePedido25.calculaSubtotal();
			DetallePedido detallePedido26 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido6.calculaSubtotal();
			pedido10.getDetallePedidos().add(detallePedido24);
			pedido10.getDetallePedidos().add(detallePedido25);
			pedido10.getDetallePedidos().add(detallePedido26);
			pedido10.setCliente(cliente2);
			pedidoRepository.save(pedido10);

			Pedido pedido11 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(250.0)
					.totalCosto(80.6)
					.estadoPedido(EstadoPedido.PAGADO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido27 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido27.calculaSubtotal();
			DetallePedido detallePedido28 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido28.calculaSubtotal();
			DetallePedido detallePedido29 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido29.calculaSubtotal();
			pedido11.getDetallePedidos().add(detallePedido27);
			pedido11.getDetallePedidos().add(detallePedido28);
			pedido11.getDetallePedidos().add(detallePedido29);
			pedido11.setCliente(cliente2);
			pedidoRepository.save(pedido11);

			Pedido pedido12 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(600.0)
					.totalCosto(300.6)
					.estadoPedido(EstadoPedido.PENDIENTE_ENTREGA)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido30 = DetallePedido.builder().articulo(empanadaCarne).cantidad(24).build();
			detallePedido30.calculaSubtotal();
			DetallePedido detallePedido31 = DetallePedido.builder().articulo(cerveza).cantidad(4).build();
			detallePedido31.calculaSubtotal();
			pedido12.getDetallePedidos().add(detallePedido30);
			pedido12.getDetallePedidos().add(detallePedido31);
			pedido12.setCliente(cliente3);
			pedidoRepository.save(pedido12);

			Pedido pedido13 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PREPARACION)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido32 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido32.calculaSubtotal();
			DetallePedido detallePedido33 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido33.calculaSubtotal();
			DetallePedido detallePedido34 = DetallePedido.builder().promocion(promocionDiaIndependencia).cantidad(1).build();
			detallePedido34.calculaSubtotal();
			pedido13.getDetallePedidos().add(detallePedido32);
			pedido13.getDetallePedidos().add(detallePedido33);
			pedido13.getDetallePedidos().add(detallePedido34);
			pedido13.setCliente(cliente2);
			pedidoRepository.save(pedido13);

			Pedido pedido14 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PENDIENTE_PAGO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido35 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido35.calculaSubtotal();
			DetallePedido detallePedido36 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido36.calculaSubtotal();
			pedido14.getDetallePedidos().add(detallePedido35);
			pedido14.getDetallePedidos().add(detallePedido36);
			pedido14.setCliente(cliente2);
			pedidoRepository.save(pedido14);

			Pedido pedido15 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PENDIENTE_ENTREGA)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente1)
					.build();

			DetallePedido detallePedido37 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido37.calculaSubtotal();
			DetallePedido detallePedido38 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido38.calculaSubtotal();
			pedido15.getDetallePedidos().add(detallePedido37);
			pedido15.getDetallePedidos().add(detallePedido38);
			pedido15.setCliente(cliente1);
			pedidoRepository.save(pedido15);

			Pedido pedido16 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.CANCELADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido39 = DetallePedido.builder().articulo(cafeSimple).cantidad(2).build();
			detallePedido39.calculaSubtotal();
			DetallePedido detallePedido40 = DetallePedido.builder().articulo(cheesecake).cantidad(4).build();
			detallePedido40.calculaSubtotal();
			pedido16.getDetallePedidos().add(detallePedido39);
			pedido16.getDetallePedidos().add(detallePedido40);
			pedido16.setCliente(cliente2);
			pedidoRepository.save(pedido16);


			Pedido pedido17 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estadoPedido(EstadoPedido.COMPLETADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido41 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido41.calculaSubtotal();
			DetallePedido detallePedido42 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido42.calculaSubtotal();
			DetallePedido detallePedido43 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido43.calculaSubtotal();
			pedido17.getDetallePedidos().add(detallePedido41);
			pedido17.getDetallePedidos().add(detallePedido42);
			pedido17.getDetallePedidos().add(detallePedido43);
			pedido17.setCliente(cliente3);
			pedidoRepository.save(pedido17);

			Pedido pedido18 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(400.0)
					.totalCosto(200.6)
					.estadoPedido(EstadoPedido.EN_CAMINO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente2)
					.build();

			DetallePedido detallePedido44 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(2).build();
			detallePedido44.calculaSubtotal();
			DetallePedido detallePedido45 = DetallePedido.builder().articulo(cocaCola).cantidad(3).build();
			detallePedido45.calculaSubtotal();
			DetallePedido detallePedido46 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido46.calculaSubtotal();
			pedido18.getDetallePedidos().add(detallePedido44);
			pedido18.getDetallePedidos().add(detallePedido45);
			pedido18.getDetallePedidos().add(detallePedido46);
			pedido18.setCliente(cliente2);
			pedidoRepository.save(pedido18);

			Pedido pedido19 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(250.0)
					.totalCosto(80.6)
					.estadoPedido(EstadoPedido.PAGADO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido47 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).build();
			detallePedido47.calculaSubtotal();
			DetallePedido detallePedido48 = DetallePedido.builder().articulo(cocaCola).cantidad(2).build();
			detallePedido48.calculaSubtotal();
			DetallePedido detallePedido49 = DetallePedido.builder().promocion(promocionDiaEnamorados).cantidad(2).build();
			detallePedido49.calculaSubtotal();
			pedido19.getDetallePedidos().add(detallePedido47);
			pedido19.getDetallePedidos().add(detallePedido48);
			pedido19.getDetallePedidos().add(detallePedido49);
			pedido19.setCliente(cliente2);
			pedidoRepository.save(pedido19);

			Pedido pedido20 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(600.0)
					.totalCosto(300.6)
					.estadoPedido(EstadoPedido.PENDIENTE_ENTREGA)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente3)
					.build();

			DetallePedido detallePedido50 = DetallePedido.builder().articulo(empanadaCarne).cantidad(24).build();
			detallePedido50.calculaSubtotal();
			DetallePedido detallePedido51 = DetallePedido.builder().articulo(cerveza).cantidad(4).build();
			detallePedido51.calculaSubtotal();
			pedido20.getDetallePedidos().add(detallePedido50);
			pedido20.getDetallePedidos().add(detallePedido51);
			pedido20.setCliente(cliente3);
			pedidoRepository.save(pedido20);

			Pedido pedido21 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PREPARACION)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido52 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido52.calculaSubtotal();
			DetallePedido detallePedido53 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido53.calculaSubtotal();
			DetallePedido detallePedido54 = DetallePedido.builder().promocion(promocionDiaIndependencia).cantidad(1).build();
			detallePedido54.calculaSubtotal();
			pedido21.getDetallePedidos().add(detallePedido52);
			pedido21.getDetallePedidos().add(detallePedido53);
			pedido21.getDetallePedidos().add(detallePedido54);
			pedido21.setCliente(cliente2);
			pedidoRepository.save(pedido21);

			Pedido pedido22 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.PENDIENTE_PAGO)
					.formaPago(FormaPago.EFECTIVO)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.sucursal(mama1)
					.domicilio(mama1.getDomicilio())
					.build();

			DetallePedido detallePedido55 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido55.calculaSubtotal();
			DetallePedido detallePedido56 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido56.calculaSubtotal();
			pedido22.getDetallePedidos().add(detallePedido55);
			pedido22.getDetallePedidos().add(detallePedido56);
			pedido22.setCliente(cliente2);
			pedidoRepository.save(pedido22);

			Pedido pedido23 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.NOTA_CREDITO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente1)
					.build();

			DetallePedido detallePedido57 = DetallePedido.builder().articulo(empanadaCarne).cantidad(1).build();
			detallePedido57.calculaSubtotal();
			DetallePedido detallePedido58 = DetallePedido.builder().articulo(cerveza).cantidad(2).build();
			detallePedido58.calculaSubtotal();
			pedido23.getDetallePedidos().add(detallePedido57);
			pedido23.getDetallePedidos().add(detallePedido58);
			pedido23.setCliente(cliente1);
			pedidoRepository.save(pedido23);

			Pedido pedido24 = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(200.0)
					.totalCosto(50.6)
					.estadoPedido(EstadoPedido.CANCELADO)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(mama1)
					.domicilio(domicilioCliente4)
					.build();

			DetallePedido detallePedido59 = DetallePedido.builder().articulo(cafeSimple).cantidad(2).build();
			detallePedido59.calculaSubtotal();
			DetallePedido detallePedido60 = DetallePedido.builder().articulo(cheesecake).cantidad(4).build();
			detallePedido60.calculaSubtotal();
			pedido24.getDetallePedidos().add(detallePedido59);
			pedido24.getDetallePedidos().add(detallePedido60);
			pedido24.setCliente(cliente2);
			pedidoRepository.save(pedido24);


		};
	}*/


}

