package ToolKit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Consola.App;




public class Restaurante {
	
	public List<Ingrediente> Ingredientes = new ArrayList<>();
	public List<ProductoMenu> ListaMenu = new ArrayList<>();
	public List<Combo> ListaCombos = new ArrayList<>();
	
	
	public Map<Integer, Pedido> Pedidos = new HashMap<>();
	private int cantidadDePedidos= 0;
	private Pedido elPedidoActual = null;
	ProductoAjustado ajustadoActual;
	Combo comboActual;
 
	

	// ************************************************************************
	// Constructor
	// ************************************************************************
	
	//Incializar el constructor del Restaurante con el mismo nombre
	
	public Restaurante() {
		
	}
	
	// ************************************************************************
	// Métodos para cargar los archivos
	// ************************************************************************
	
	public void cargarInformacionRestaurante(File archivoIngredientes,File archivoMenu,File archivoCombos )
	throws FileNotFoundException, IOException, HamburguesaException {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	public void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException, IOException, HamburguesaException {
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
			BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));	
			String linea = br.readLine();
			
			while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{	
				try{
				//Separar los valores que estaban en una línea
				String[] partes = linea.split(";");
				String unIngrediente = partes[0];
				int elPrecio = Integer.parseInt(partes[1]);
				
				//Si el ingrediente no existe, lo agrega a la lista de Ingredientes
				Ingrediente elIngrediente = buscarIngrediente(Ingredientes, unIngrediente);
				if (elIngrediente == null)
				{
					elIngrediente = new Ingrediente(unIngrediente,elPrecio);
					Ingredientes.add(elIngrediente);
				}
				else{
					throw new IngredienteRepetidoException("El ingrediente ya se encuentra registrado");
					
				}

				}

				catch(HamburguesaException e){
					System.out.println(e.getMessage());

				}
				linea = br.readLine(); // Leer la siguiente línea
				
			}			
	}
	
	
	
	public void cargarMenu(File archivoMenu) throws FileNotFoundException, IOException, ProductoRepetidoException {
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
			BufferedReader br = new BufferedReader(new FileReader(archivoMenu));	
			String linea = br.readLine();
			
			while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				try{
				//Separar los valores que estaban en una línea
				String[] partes = linea.split(";");
				String unMenu = partes[0];
				int elPrecio = Integer.parseInt(partes[1]);
				
				

				for (ProductoMenu cadamenu:ListaMenu){
					if (cadamenu.getNombre().equals(unMenu) ){
						throw new ProductoRepetidoException("Producto del menu");
					}
					
				}

				//Crear un nuevo objeto del Menu
				ProductoMenu elMenu  = new ProductoMenu(unMenu,elPrecio);
				ListaMenu.add(elMenu);
				}

				catch(HamburguesaException e){
					System.out.println(e.getMessage());
				}

				linea = br.readLine(); // Leer la siguiente línea
				
			}			
		
	}
	
	public void cargarCombos(File archivoCombos) throws FileNotFoundException, IOException, ProductoRepetidoException {
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
			BufferedReader br = new BufferedReader(new FileReader(archivoCombos));	
			String linea = br.readLine();
			
			while (linea != null) // Cuando se llegue al final del archivo, linea tendrá el valor null
			{
				try{
				//Separar los valores que estaban en una línea
				String[] partes = linea.split(";");
				String unCombo = partes[0];
				double elDescuento = Double.parseDouble(partes[1].replace("%",""))/100;
				String nombreComboPeq = partes[2];
				String papas = partes[3];
				String gaseosa = partes[4];

				
				//Inicializar variable para usar el for
				int preciobase = 0;
				
				for (ProductoMenu elem: ListaMenu) {
					String nombre = elem.getNombre();
					if (nombre.equals(nombreComboPeq) || nombre.equals(papas) || nombre.equals(gaseosa)) {
						int precio = elem.getPrecio();
						preciobase += precio;
					}
				}

				for (Combo cadacombo:ListaCombos){
					if (cadacombo.getNombre().equals(unCombo)){
						throw new ProductoRepetidoException("Producto del Combo");
					}
					
				}
				
				//Crear un nuevo objeto del Combo 
				Combo elCombo  = new Combo(unCombo,elDescuento, papas, gaseosa, preciobase);
				ListaCombos.add(elCombo);

			}

			catch(HamburguesaException e){
				System.out.println(e.getMessage());
			}
				
				
				
				linea = br.readLine(); // Leer la siguiente línea
				
			}			
		
	}
	
	
	
	//Método auxiliar para observar si ya existe el producto dentro de la lista
	
		private Ingrediente buscarIngrediente(List<Ingrediente> Ingredientes, String nombreIngrediente)
		{
			Ingrediente elIngrediente = null;

			for (int i = Ingredientes.size() - 1; i >= 0 && elIngrediente == null; i--)
			{
				Ingrediente unIngrediente = Ingredientes.get(i);
				if (unIngrediente.getName().equals(nombreIngrediente))
				{
					elIngrediente = unIngrediente;
					return elIngrediente;
				}
			}

			return elIngrediente;
			
		}
	
	// ************************************************************************
	// Getters
	// ************************************************************************
	
	
	
	//Modificación: No se recibe la ListaMenu si no la posicion del productoBase que se quiere modificar
	public Object getProductoMenu(int iBase) {
		
		boolean encontrado = false;
	    int index = 0;
	    ProductoMenu resultado = ListaMenu.get(0);
	    
	    while (!encontrado && index < ListaMenu.size()) {
	    	ProductoMenu objetoActual = ListaMenu.get(index);
	        if (index+1 == iBase) {
	            encontrado = true;
	            resultado = objetoActual;
	        }
	        index++;
	    }
	    
	    if (!encontrado) {
	    	String resultadoStr = "Producto no disponible, intente con uno de los mostrados en el menú";
	    	return resultadoStr;
	    }
	    
	    return resultado;
	}
	
	
	//Modificación: No se recibe la ListaMenu si no el ingrediente que se quiere modificar
	public Object getIngredientes(int iIngrediente) {
		boolean encontrado = false;
	    int index = 0;
	    Ingrediente resultado = Ingredientes.get(0);
	    
	    while (!encontrado && index < Ingredientes.size()) {
	    	Ingrediente objetoActual = Ingredientes.get(index);
	        if (index+1 == iIngrediente) {
	            encontrado = true;
	            resultado = objetoActual;
	        }
	        index++;
	    }
	    
	    if (!encontrado) {
	    	String resultadoStr = "Ingrediente no disponible, intente con uno de los mostrados en el menú";
	    	return resultadoStr;
	    }
		
	    return resultado;
	}
	
	
	//Modificación: Se agregó una función para encontrar el objeto de un combo
	
	public Object getCombo(int icomboBase) {
		boolean encontrado = false;
	    int index = 0;
	    Combo resultado = ListaCombos.get(0);
	    
	    while (!encontrado && index < ListaCombos.size()) {
	    	Combo objetoActual = ListaCombos.get(index);
	        String nombreActual = objetoActual.getNombre(); //nombre de el objeto de la clase ingrediente actual
	        if (index+1 == icomboBase) {
	            encontrado = true;
	            resultado = objetoActual;
	        }
	        index++;
	    }
	    
	    if (!encontrado) {
	    	String resultadoStr = "Combo no disponible, intente con uno de los mostrados en el menú";
	    	return resultadoStr;
	    }
		
	    return resultado;
	}

	public Producto EscogerProducto(int codigoProducto) {
		//retorna un producto a partir del código que ingresa el usuario por parámetro
		int j=-1;
		for (int i=0; i<ListaMenu.size();i++) {
			if (i+1==codigoProducto) {
				return ListaMenu.get(i);//recorre los ListaMenu
			}
			j+=1;
		}
		for (int i=0; i<ListaCombos.size();i++) {
			if (j+i+2==codigoProducto) {
				return ListaCombos.get(i);//recorre los combos
			}
		}
		return null;
	}

	public Ingrediente EscogerIngrediente(int codigoIngrediente) {
		//retorna un ingrediente a partir del código que ingresa el usuario por parámetro
		for (int i=0; i<Ingredientes.size();i++) {
			if (i+1==codigoIngrediente) {
				return Ingredientes.get(i);
			}
		}
		return null;
	}
	
	// ************************************************************************
	// Funciones del pedido
	// ************************************************************************
	
	
	public void iniciarpedido(String nombreCliente, String direccionCliente) {
		if (elPedidoActual == null) {
			elPedidoActual = new Pedido(nombreCliente, direccionCliente);
		}
		
		else {
			System.out.println("Ya hay un pedido en curso, terminelo antes de iniciar uno nuevo");
		}
	
	}
	
	//Modificación: Crear una funcion para crear un nuevo productoajustado
	
	public void crearProductoAjustado(Producto Base) {
		//Inicia el producto ajustado llamando al constructor con dos listas de Ingredientes vacías
		List<Ingrediente> listaExtras= new ArrayList<Ingrediente>();
		List<Ingrediente> listaRemoved= new ArrayList<Ingrediente>();
	
	ajustadoActual= new ProductoAjustado(Base, listaExtras, listaRemoved);
}
	
	
	//Modificación: Crear una función para añadir un nuevo ingrediente a producto ajustado
	
	public void añadirIngProdAjus(Ingrediente ingredient) {
		//Añade a la lista de ingredientes extra de el producto ajustado
		ajustadoActual.getExtras().add(ingredient);

		
	}
	
	//Modificación: Crear una función para eliminar un  ingrediente a producto ajustado
	
	public void eliminarIngProdAjus(Ingrediente ingrediente) {
		//Añade a la lista de ingredientes eliminados de el producto ajustado
		ajustadoActual.getRemoved().add(ingrediente);
		
	}
	//Modificación: Crear una función que devuelve el producto ajustado actual
	
	public ProductoAjustado getProductoAjustadoActual() {
		return ajustadoActual;
	}
	
	
	//Modificación: Agregar item a Combo
	public void agregarItemaCombo(Object object, ProductoMenu itemCombo, int i) {
		Object ComboO = getCombo(i);
		if (ComboO instanceof Combo) {
			Combo Combo = (Combo) ComboO;
			Combo.agregaritemACombo(itemCombo);
		}
		
		else if (ComboO instanceof String) { //en caso de que el producto dentro de menu no esté 
			String novalido = (String) ComboO;
			System.out.println(novalido);
		}
		
	}
	
	public Pedido getPedidoEnCurso() {
		//getter del pedido en curso
		if (elPedidoActual==null) {
			System.out.println("\nNo hay una orden en curso\n");
			return null;
		}

		return elPedidoActual;
	}
	
	public void cerraryGuardarPedido() {
		//Guarda el historial de pedidos en un mapa y un archivo .txt incluyendo fecha y hora del pedido
		Pedidos.put(cantidadDePedidos, elPedidoActual);//Agrega al mapa usando como llave el id del pedido
	
		String factura= elPedidoActual.generarTextoFactura();//Además de generar el string de la factura, imprime en pantalla esta misma
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = currentDateTime.format(formatter);//String con la fecha y hora que registra el computador
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/historialPedidos.txt", true))) {
			writer.write(formattedDateTime+ "\n\n"+ factura);//Escribe en el archivo historialPedidos.txt
			} catch (IOException e) {
			e.printStackTrace();
			}

		elPedidoActual=null;//Indica que ya no hay ningún pedido activo
	}
	public void getIngredientes() {
		//Muestra en pantalla los ingredientes numerados desde el 1 y con su precio
		System.out.println("\nAvailable Ingredients: \n");

		for (int i=0; i<Ingredientes.size();i++) {
			Ingrediente ingrediente= Ingredientes.get(i);
			String nombreIngrediente= ingrediente.getName();
			int precioIngrediente= ingrediente.getCostoAdicional();
			System.out.print(i+1);
			System.out.print(". "+ nombreIngrediente+" ");
			System.out.println(precioIngrediente);
			}
			
	}
	
	
	
	
	//Modificación: mostrar todos los productos y combos disponibles
	
		public void getMenuProductos() {
			System.out.println("Productos Individuales Disponibles: \n");
			int numero = 1;
			for (Producto elem: ListaMenu) {
				
				System.out.println(numero + elem.getNombre() + " $" + Integer.toString(elem.getPrecio()));
				numero +=1;
			}
			
			System.out.println("\n");
			
			System.out.println("Combos Disponibles: \n");
			numero = 1;
			for (Producto elem: ListaCombos) {
				System.out.println(numero + " " + elem.getNombre() + " $" + Integer.toString(elem.getPrecio()));
				numero += 1;
				
			}
		}
		
		public void getMenuIngredientes() {
			System.out.println("Ingredientes Disponibles: \n");
			int numero = 1;
			for (Ingrediente elem: Ingredientes) {
				
				System.out.println(numero + elem.getName() + " $" + Integer.toString(elem.getCostoAdicional()));
				numero +=1;
			}
		}
		public Pedido getPedidoById (int Id) {
			//getter de un pedido desde el mapa historial "allPedidos"
			return Pedidos.get(Id);
		}
}
	

