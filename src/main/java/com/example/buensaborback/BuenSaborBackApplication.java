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
		logger.info("Estoy activo en el main Alberto");
	}


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
			sucursalRepository.save(mama1);

			mama2.getCategorias().add(categoriaGaseosas);
			mama2.getCategorias().add(categoriaBebidas);
			mama2.getCategorias().add(categoriaEmpanadas);
			sucursalRepository.save(mama2);

			massa1.getCategorias().add(categoriaPizzas);
			massa1.getCategorias().add(categoriaTragos);
			massa1.getCategorias().add(categoriaGaseosas);
			massa1.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(massa1);

			massa2.getCategorias().add(categoriaPizzas);
			massa2.getCategorias().add(categoriaTragos);
			massa2.getCategorias().add(categoriaGaseosas);
			massa2.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(massa2);

			mansa1.getCategorias().add(categoriaHamburguesas);
			mansa1.getCategorias().add(categoriaTragos);
			mansa1.getCategorias().add(categoriaGaseosas);
			massa1.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(mansa1);

			mansa2.getCategorias().add(categoriaHamburguesas);
			mansa2.getCategorias().add(categoriaTragos);
			mansa2.getCategorias().add(categoriaGaseosas);
			massa2.getCategorias().add(categoriaBebidas);
			sucursalRepository.save(mansa2);

			cacerito1.getCategorias().add(categoriaGaseosas);
			cacerito1.getCategorias().add(categoriaBebidas);
			cacerito1.getCategorias().add(categoriaEmpanadas);
			sucursalRepository.save(cacerito1);

			cacerito2.getCategorias().add(categoriaGaseosas);
			cacerito2.getCategorias().add(categoriaBebidas);
			cacerito2.getCategorias().add(categoriaEmpanadas);
			sucursalRepository.save(cacerito2);

			aromas1.getCategorias().add(categoriaCafe);
			aromas1.getCategorias().add(categoriaPasteleria);
			sucursalRepository.save(aromas1);

			aromas2.getCategorias().add(categoriaCafe);
			aromas2.getCategorias().add(categoriaPasteleria);
			sucursalRepository.save(aromas2);

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
			StockInsumoSucursal stockHarinaSuc1=StockInsumoSucursal.builder().stockActual(4000).stockMinimo(500).stockMaximo(4000).articuloInsumo(harina).sucursal(massa1).build();
			StockInsumoSucursal stockQuesoSuc1=StockInsumoSucursal.builder().stockActual(3000).stockMinimo(1000).stockMaximo(4000).articuloInsumo(queso).sucursal(massa1).build();
			StockInsumoSucursal stockTomateSuc1=StockInsumoSucursal.builder().stockActual(30).stockMinimo(10).stockMaximo(40).articuloInsumo(tomate).sucursal(massa1).build();
			StockInsumoSucursal stockCervezaSuc1=StockInsumoSucursal.builder().stockActual(30).stockMinimo(10).stockMaximo(70).articuloInsumo(cerveza).sucursal(massa1).build();

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

			// Crear promocion para sucursal - Dia de los enamorados
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
		};
	}
}