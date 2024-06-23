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

			// Crear 1 empresa, 2 sucursales para esa empresa y los Domicilios para esas sucursales

			Empresa empresaCarlos = Empresa.builder().nombre("Lo de Carlos").cuit(30546780L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(empresaCarlos);

			Sucursal sucursalGuaymallen = Sucursal.builder().
					nombre("En Guaymallen").horarioApertura(LocalTime.of(17, 0)).
					horarioCierre(LocalTime.of(23, 0)).
					esCasaMatriz(true).
					build();

			Sucursal sucursalMarDelPlata = Sucursal.builder().nombre("En MDQ").
					horarioApertura(LocalTime.of(16, 0)).
					horarioCierre(LocalTime.of(23, 30)).build();

			Domicilio domicilioBerutti = Domicilio.builder().cp(5519).calle("Berutti").numero(2684).piso(0).nroDpto(5).
					localidad(localidad1).build();

			Domicilio domicilioGaboto = Domicilio.builder().cp(7600).calle("Gaboto").numero(3475).
					localidad(localidad2).build();
			// GRABAMOS DOMICILIOS
			//domicilioRepository.save(domicilioBerutti);
			//domicilioRepository.save(domicilioGaboto);

			//ASOCIAMOS LOS DOMICILIOS A SUCURSAL
			sucursalGuaymallen.setDomicilio(domicilioBerutti);
			sucursalMarDelPlata.setDomicilio(domicilioGaboto);

			//ASOCIAMOS SUCURSALES A EMPRESA
			empresaCarlos.getSucursales().add(sucursalGuaymallen);
			empresaCarlos.getSucursales().add(sucursalMarDelPlata);

			//ASIGNAMOS EMPRESA A SUCURSALES
			sucursalGuaymallen.setEmpresa(empresaCarlos);
			sucursalMarDelPlata.setEmpresa(empresaCarlos);
			// Grabo las sucursales
			sucursalRepository.save(sucursalGuaymallen);
			sucursalRepository.save(sucursalMarDelPlata);
			// Grabi empresa
			empresaRepository.save(empresaCarlos);

			// Crear Categorías de productos y subCategorías de los mismos
			Categoria categoriaBebidas = Categoria.builder().denominacion("Bebidas").esInsumo(true).
					build();
			categoriaRepository.save(categoriaBebidas);

			Categoria categoriaGaseosas = Categoria.builder().denominacion("Gaseosas").categoriaPadre(categoriaBebidas).esInsumo(true)
					.build();
			categoriaRepository.save(categoriaGaseosas);

			Categoria categoriaTragos = Categoria.builder().denominacion("Tragos").categoriaPadre(categoriaBebidas).esInsumo(true).
					build();
			categoriaRepository.save(categoriaTragos);

			Categoria categoriaPizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).
					build();

			Categoria categoriaInsumos = Categoria.builder().denominacion("Insumos").esInsumo(true).
					build();

			// Grabo la categoría de insumos y de Manufacturados
			categoriaRepository.save(categoriaPizzas);
			categoriaRepository.save(categoriaInsumos);
			// Asigno subCategorías

			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);
			// Grabo las Subcategorías
			categoriaRepository.save(categoriaBebidas);

			logger.info("---------------voy a asignar a Guaymallen--------------------");
			//ASOCIAMOS CATEGORIAS CON SUCURSAL
			categoriaInsumos.getSucursales().add(sucursalGuaymallen);
			// Cargo las categorias a la sucursal guaymallen
			sucursalGuaymallen.getCategorias().add(categoriaInsumos);
			sucursalGuaymallen.getCategorias().add(categoriaBebidas);
			sucursalGuaymallen.getCategorias().add(categoriaGaseosas);
			sucursalGuaymallen.getCategorias().add(categoriaTragos);
			sucursalGuaymallen.getCategorias().add(categoriaPizzas);
			logger.info("{}", sucursalGuaymallen);
			// Grabo las categorias que vende esa sucursal
			sucursalRepository.save(sucursalGuaymallen);

			logger.info("---------------saque el save de abajo-------------------");

			logger.info("---------------grabe guaymallen--------------------");

			logger.info("---------------voy a asignar a Mardel Plata--------------------");
			categoriaInsumos.getSucursales().add(sucursalMarDelPlata);
			// Cargo las categorias a la sucursal Mardel Plata
			sucursalMarDelPlata.getCategorias().add(categoriaInsumos);
			sucursalMarDelPlata.getCategorias().add(categoriaBebidas);
			sucursalMarDelPlata.getCategorias().add(categoriaGaseosas);
			sucursalMarDelPlata.getCategorias().add(categoriaTragos);
			sucursalMarDelPlata.getCategorias().add(categoriaPizzas);
			// Grabo las categorias que vende esa sucursal
			sucursalRepository.save(sucursalMarDelPlata);

			logger.info("---------------grabe Mardel Plata--------------------");

			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().denominacion("Litros").build();
			UnidadMedida unidadMedidaGramos = UnidadMedida.builder().denominacion("Gramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().denominacion("Cantidad").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaGramos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);

			// Crear Insumos , coca cola , harina , etc
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

			// crear fotos para cada insumo
			ImagenArticulo imagenArticuloCoca = ImagenArticulo.builder().
					url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").
					build();
			ImagenArticulo imagenArticuloHarina = ImagenArticulo.builder().url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
			ImagenArticulo imagenArticuloQueso = ImagenArticulo.builder().url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
			ImagenArticulo imagenArticuloTomate = ImagenArticulo.builder().url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();
			imagenArticuloRepository.save(imagenArticuloCoca);
			imagenArticuloRepository.save(imagenArticuloHarina);
			imagenArticuloRepository.save(imagenArticuloQueso);
			imagenArticuloRepository.save(imagenArticuloTomate);

			//ASOCIAMOS IMAGEN CON INSUMOS
			cocaCola.getImagenes().add(imagenArticuloCoca);
			harina.getImagenes().add(imagenArticuloHarina);
			queso.getImagenes().add(imagenArticuloQueso);
			tomate.getImagenes().add(imagenArticuloTomate);
			// Grabamos los Articulos


			//Asociamos el Stock a la sucursal
			StockInsumoSucursal stockCocacolaSuc1=StockInsumoSucursal.builder().
					stockActual(15).
					stockMinimo(10).
					stockMaximo(50).
					articuloInsumo(cocaCola).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal stockHarinaSuc1=StockInsumoSucursal.builder().stockActual(4).stockMinimo(10).stockMaximo(40).articuloInsumo(harina).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal stockQuesoSuc1=StockInsumoSucursal.builder().stockActual(4).stockMinimo(10).stockMaximo(40).articuloInsumo(queso).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal stockTomateSuc1=StockInsumoSucursal.builder().stockActual(4).stockMinimo(10).stockMaximo(40).articuloInsumo(tomate).sucursal(sucursalGuaymallen).build();

	harina.getStocksInsumo().add(stockHarinaSuc1);
	cocaCola.getStocksInsumo().add(stockCocacolaSuc1);
	queso.getStocksInsumo().add(stockQuesoSuc1);
	tomate.getStocksInsumo().add(stockTomateSuc1);

			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			//ASOCIAMOS CATEGORIA CON INSUMOS
			categoriaInsumos.getArticulos().add(harina);
			categoriaInsumos.getArticulos().add(queso);
			categoriaInsumos.getArticulos().add(tomate);
			categoriaGaseosas.getArticulos().add(cocaCola);
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

			//	categoriaRepository.save(categoriaGaseosas); CREO QUE ESTA DE MAS REVISAR


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
			//sucursalRepository.save(sucursalGuaymallen);
			//	sucursalRepository.save(sucursalMarDelPlata);


//			sucursalRepository.guardarSucursalConValidacion(sucursalGuaymallen);

//			sucursalRepository.guardarSucursalConValidacion(sucursalMarDelPlata);

			//Crea un cliente y un usuario
			ImagenPersona imagenCliente = ImagenPersona.builder().url("https://hips.hearstapps.com/hmg-prod/images/la-la-land-final-1638446140.jpg").build();
			imagenPersonaRepository.save(imagenCliente);
			ImagenPersona imagenEmpleado = ImagenPersona.builder().url("https://hips.hearstapps.com/hmg-prod/images/la-la-land-final-1638446140.jpg").build();
			imagenPersonaRepository.save(imagenEmpleado);
			Domicilio domicilioCliente = Domicilio.builder().cp(5519).calle("Cangallo").numero(800).piso(0).nroDpto(1).localidad(localidad1).build();
			domicilioRepository.save(domicilioCliente);
			UsuarioCliente usuarioCliente = UsuarioCliente.builder().email("enferrelariel@hotmail.com").build();
			usuarioCliente.setClave("eee");
			usuarioClienteRepository.save(usuarioCliente);

			UsuarioCliente usuarioCliente2 = UsuarioCliente.builder().email("pepe@gmail.com").build();
			usuarioCliente2.setClave("asd");
			usuarioClienteRepository.save(usuarioCliente2);
			Usuario usuarioEmpleado = Usuario.builder().username("martin").auth0Id("9565a49d-ecc1-4f4e-adea-6cdcb7edc43a").rol(Rol.CAJERO).build();
			usuarioRepository.save(usuarioEmpleado);

			Cliente cliente = new Cliente();

			cliente.setImagenPersona(imagenCliente);
			//cliente.setEmail("correoFalso@gmail.com");
			cliente.setNombre("Sebastian");
			cliente.setApellido("Wilder");
			cliente.setUsuarioCliente(usuarioCliente);
			cliente.setTelefono("2615920825");
			//	cliente.setEstaActivo(true);
			cliente.getDomicilios().add(domicilioCliente);
			clienteRepository.save(cliente);

			Empleado empleado = new Empleado();

			//empleado.setEmail("correoFalso@hotmail.com");
			empleado.setNombre("CorreoFalso");
			empleado.setApellido("Falsin");
			empleado.setUsuario(usuarioEmpleado);
			empleado.setTelefono("2612151170");
			//	empleado.setEstaActivo(true);
			empleado.setImagenPersona(imagenEmpleado);
			empleado.setSucursal(sucursalGuaymallen);
			sucursalGuaymallen.getEmpleados().add(empleado);
			empleadoRepository.save(empleado);
			logger.info("Empleado{}:", empleado);


			//Crea un pedido para el cliente
			Pedido pedido = Pedido.builder().fechaPedido(LocalDate.now())
					.horaEstimadaFinalizacion(LocalTime.now())
					.total(300.0)
					.totalCosto(170.6)
					.estadoPedido(EstadoPedido.PREPARACION)
					.formaPago(FormaPago.MERCADO_PAGO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.sucursal(sucursalGuaymallen)
					.domicilio(domicilioCliente)
					.build();

			DetallePedido detallePedido1 = DetallePedido.builder().articulo(pizzaMuzarella).cantidad(1).subTotal(200.0).build();
			DetallePedido detallePedido2 = DetallePedido.builder().articulo(cocaCola).cantidad(2).subTotal(100.0).build();

			pedido.getDetallePedidos().add(detallePedido1);
			pedido.getDetallePedidos().add(detallePedido2);
			pedido.setCliente(cliente);
			pedido.setEmpleado(empleado);
			pedidoRepository.save(pedido);

			Random random = new Random();
			/*Factura facturaBuilder = Factura.builder().fechaFacturacion(LocalDate.now())
					.mpPaymentId(random.nextInt(1000))  // Se asume un rango máximo de 1000
					.mpMerchantOrderId(random.nextInt(1000)) // Se asume un rango máximo de 1000
					.mpPreferenceId("MP-" + random.nextInt(10000))  // Se asume un rango máximo de 10000
					.mpPaymentType("Tipo" + random.nextInt(10)) // Se asume un rango máximo de 10
					.formaPago(FormaPago.EFECTIVO)
					.totalVenta(random.nextDouble() * 1000).build();

			facturaRepository.save(facturaBuilder);

			pedido.setFactura(facturaBuilder);*/

			pedidoRepository.save(pedido);


			//Prueba de carga perezosa
			//Empresa-Sucursal
			//Sucursal-Promocion
			//Sucursal-Categoria
			//Sucursal-Empleado

			Domicilio domicilioSucu1 = Domicilio.builder().cp(5519).calle("calle1").numero(2684).piso(0).nroDpto(5).
					localidad(localidad1).build();

			//domicilioRepository.save(domicilioSucu1);

			Domicilio domicilioSucu2 = Domicilio.builder().cp(5519).calle("calle2").numero(2684).piso(0).nroDpto(5).
					localidad(localidad1).build();
			//domicilioRepository.save(domicilioSucu2);

			Sucursal sucursal = Sucursal.builder()
					.nombre("sucursal prueba")
					.domicilio(domicilioSucu1)
					.horarioApertura(LocalTime.of(12, 30, 00))
					.horarioApertura(LocalTime.of(20, 00, 00))
					.build();
			sucursalRepository.save(sucursal);
			Sucursal sucursal2 = Sucursal.builder()
					.nombre("sucursal prueba2")
					.domicilio(domicilioSucu2)
					.horarioApertura(LocalTime.of(12, 30, 00))
					.horarioApertura(LocalTime.of(20, 00, 00))
					.build();
			sucursalRepository.save(sucursal2);

			Empresa empresa = Empresa.builder()
					.nombre("Empresa de prueba")
					.cuit(999999999L)
					.razonSocial("Razon social")
					.build();
			empresaRepository.save(empresa);
			empresa.getSucursales().add(sucursal);
			sucursal.setEmpresa(empresa);
			sucursalRepository.save(sucursal);
			sucursal2.setEmpresa(empresa);
			sucursalRepository.save(sucursal2);

			empresa.getSucursales().add(sucursal2);
			            empresaRepository.save(empresa);


			Categoria categoria = Categoria.builder()
					.denominacion("Categoria de prueba")
					.build();
			categoriaRepository.save(categoria);

			/*
			//PRUEBA LAZY -> FALLA
			var empresaRepo = empresaRepository.findById(2L);
			if(empresaRepo.isPresent()){
				Optional<Sucursal> sucursalRepo = sucursalRepository.findById(3L);
				Optional<Sucursal> sucursalRepo2 = sucursalRepository.findById(4L);
				if(sucursalRepo2.isPresent() && sucursalRepo.isPresent()){
					empresaRepo.get().getSucursales().add(sucursalRepo.get());
					empresaRepo.get().getSucursales().add(sucursalRepo2.get());
					empresaRepository.save(empresaRepo.get());
				}
			}*/
			/*
			var categoriaRep = categoriaRepository.findWithSucursalesById(6L);//CON FINDBYID NO SE PUEDE AÑADIR SUCURSALES POR LAZY
			var empresaRepo = empresaRepository.findWithSucursalesById(2L);
			Sucursal sucursalRepo = sucursalRepository.findWithEmpleadosById(3L);//CON FINDBYID NO SE PUEDE AÑADIR EMPLEADOS POR LAZY
			Optional<Sucursal> sucursalRepo2 = sucursalRepository.findById(4L);
			Sucursal sucursalRepo3 = sucursalRepository.findWithCategoriasById(3L);//CON FINDBYID NO SE PUEDE AÑADIR CATEGORIAS POR LAZY
			if(sucursalRepo2.isPresent()){
				empresaRepo.getSucursales().add(sucursalRepo);
				empresaRepo.getSucursales().add(sucursalRepo2.get());
				empresaRepository.save(empresaRepo);
				sucursalRepo.setEmpresa(empresaRepo);
				sucursalRepo2.get().setEmpresa(empresaRepo);
				sucursalRepo3.getCategorias().add(categoriaRep);
				sucursalRepository.save(sucursalRepo);
				sucursalRepository.save(sucursalRepo2.get());
				sucursalRepository.save(sucursalRepo3);
				categoriaRep.getSucursales().add(sucursalRepo);
				categoriaRep.getSucursales().add(sucursalRepo2.get());
				var empleado1 = empleadoRepository.findById(2L);
				if(empleado1.isPresent()){
					sucursalRepo.getEmpleados().add(empleado1.get());

					sucursalRepository.save(sucursalRepo);
				}
			}

			logger.info("------------Nombre de sucursales de la empresa id 2------------");
			empresaRepo.getSucursales()
					.stream()
					.map(Sucursal::getNombre)
					.forEach(logger::info);

			logger.info("------------Nombre de empresa de la sucursal id 3------------");
			logger.info("{}",sucursalRepo.getEmpresa().getNombre());
			logger.info("------------Empleados de la sucursal id 3------------");
			sucursalRepo.getEmpleados().stream()
					.map(Empleado::getNombre)
					.forEach(logger::info);

			logger.info("------------Nombre de empresa de la sucursal id 4------------");
			logger.info("{}",sucursalRepo2.get().getEmpresa().getNombre());

			logger.info("----------------Sucursal Guaymallen ---------------------");
			logger.info("{}",sucursalGuaymallen);
			logger.info("----------------Sucursal Mardel Plata ---------------------");
			logger.info("{}",sucursalMarDelPlata);
			logger.info("----------------Pedido ---------------------");
			logger.info("{}",pedido);
		};
	}*/
		};
	}
}