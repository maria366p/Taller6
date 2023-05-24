package Consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import ToolKit.Combo;
import ToolKit.CostoPedidoException;
import ToolKit.HamburguesaException;
import ToolKit.Ingrediente;
import ToolKit.ProductoMenu;
import ToolKit.Restaurante;




public class App {
	 //public static void main(String[] args) throws Exception {
    	
		/**
		 * Este es el restaurante que se usará para hacer la mayoría de las
		 * operaciones de la aplicación. Este restaurante también contiene toda la
		 * información sobre los menus después de que se cargue desde un archivo.
		 */
		static Restaurante restaurante = new Restaurante();
    	
		/**
    	 * Ejecuta la aplicación: le muestra el menú al usuario y la pide que ingrese
    	 * una opción, y ejecuta la opción seleccionada por el usuario. Este proceso se
    	 * repite hasta que el usuario seleccione la opción de abandonar la aplicación.
		 * @throws HamburguesaException
		 * @throws CostoPedidoException
    	 */
	
    public static void iniciar_programa() throws FileNotFoundException, IOException, HamburguesaException, CostoPedidoException {

		boolean loaded = false;
		boolean centinel= true;
    	
		while (centinel) {

			mostrarMenu();
    		int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
    		
    		if (opcion_seleccionada==1 && !loaded) {
				File archivoIngredientes = new File("Data/ingredientes.txt");
				File archivoMenu = new File("Data/menu.txt");
				File archivoCombos = new File("Data/combos.txt");
				restaurante.cargarInformacionRestaurante(archivoIngredientes,archivoMenu,archivoCombos);
		    			System.out.println("\nCarga Exitosa.");
		    			loaded= true;
    		}else if(opcion_seleccionada==0) {
			    			System.out.println("\nThanks for using the Program.");
			    			centinel=false;
    		}else if(opcion_seleccionada==2) {
    						restaurante.getMenuIngredientes();
    		}else if(opcion_seleccionada==3) {
				restaurante.getMenuProductos();
    		}else if(opcion_seleccionada==4) {
    			String nombreCliente= input("\nNombre Completo:");
    			String direccionCliente= input("\nDirección de residencia:");
    			restaurante.iniciarpedido(nombreCliente, direccionCliente);
    			boolean abierto= true;
    			boolean wantToAdd;
    			boolean wantToRemove;
    			
		    		while (abierto) {
		    			boolean adjusted= false;
		    			System.out.println("\nEscoja un producto:\n");
		    			restaurante.getMenuProductos();
		    			int productoElegidoCode = Integer.parseInt(input("\nPor favor seleccione una opcion\n"));
		    			//elRestaurante.getPedidoEnCurso().agregarProducto(elRestaurante.choseProduct(productoElegidoCode));
		    			
		    			
		    			int noAdd = Integer.parseInt(input("\nDesea AÑADIR un ingrediente?\n1. Si\n2. No"));
		    			if (noAdd==1) {
		    				wantToAdd= true;
		    				adjusted= true;
		    				restaurante.crearProductoAjustado(restaurante.EscogerProducto(productoElegidoCode));
		    			}else {
		    				wantToAdd= false;
		    			}
		    				while(wantToAdd) {
		    					restaurante.getMenuIngredientes();
		    					int ingredienteExtraCode = Integer.parseInt(input("\nPor favor seleccione una opción"));
		    					restaurante.añadirIngProdAjus(restaurante.EscogerIngrediente(ingredienteExtraCode));
		    					
		    					int continueExtra = Integer.parseInt(input("\nDesea AÑADIR algo mas?\n1. Si\n2. No"));
		    					if (continueExtra==1) {
				    				wantToAdd= true;
				    			}else {
				    				wantToAdd= false;
				    			}
		    				}

			    			int noRemove = Integer.parseInt(input("\nDesea ELIMINAR algún ingrediente?\n1. Si\n2. No"));
			    			if (noRemove==1) {
			    				wantToRemove= true;
			    				if(!adjusted) {
			    					restaurante.crearProductoAjustado(restaurante.EscogerProducto(productoElegidoCode));
			    				}
			    			}else {
			    				wantToRemove= false;
			    			}
			    				while(wantToRemove) {
			    					restaurante.getMenuIngredientes();
			    					int ingredienteRemoveCode = Integer.parseInt(input("\nPor favor seleccione una opcion"));
			    					restaurante.eliminarIngProdAjus(restaurante.EscogerIngrediente(ingredienteRemoveCode));
			    					
			    					int continueRemove = Integer.parseInt(input("\nDesea Eliminar algo mas?\n1. Si\n2. No"));
			    					if (continueRemove==1) {
					    				wantToRemove= true;
					    			}else {
					    				wantToRemove= false;
					    			}
			    					
			    				}
			    			
			    			if(adjusted) {
			    				restaurante.getPedidoEnCurso().agregarProducto(restaurante.getProductoAjustadoActual());
			    			}else {
			    				restaurante.getPedidoEnCurso().agregarProducto(restaurante.EscogerProducto(productoElegidoCode));
			    			}
			    				
			    			int wantToClose = Integer.parseInt(input("\nDesea Ordenar algo mas?\n1. Si\n2. No"));
			    			if (wantToClose==1) {
			    				abierto= true;
			    			}else {
			    				abierto= false; //Se cierra el pedido
			    				System.out.println("\nPedido Cerrado.\n");
			    				restaurante.cerraryGuardarPedido();			    			}
		    		}
    		}
			
			else if (opcion_seleccionada==4) {//muestra la factura de un pedido por su Id
    			int idBuscado= Integer.parseInt(input("\nCuál es el id de la orden?"));
    			restaurante.getPedidoById(idBuscado).generarTextoFactura();
    		}

		}



	}

	public static void mostrarMenu()
    	{
		System.out.println("\nOpciones de la aplicación\n");
    	System.out.println("1. Iniciar un nuevo pedido");
    	System.out.println("2. Mostrar Ingredientes");
    	System.out.println("3. Mostrar Productos");
    	System.out.println("4. Empezar Orden\n");
		System.out.println("0. Salir de la aplicación\n");
    	}
    



	/**
    	* Este método sirve para imprimir un mensaje en la consola pidiéndole
    	* información al usuario y luego leer lo que escriba el usuario.
    	* 
    	* @param mensaje El mensaje que se le mostrará al usuario
    	* @return La cadena de caracteres que el usuario escriba como respuesta.
    	*/
    	public static String input(String mensaje)
    	{
	    	try
	    	{
		    	System.out.print(mensaje + ": ");
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    	return reader.readLine();
	    	}
	    	catch (IOException e)
	    	{
		    	System.out.println("Error leyendo de la consola");
		    	e.printStackTrace();
	    	}
	    	return null;
    	}
	public static void main(String[] args) throws Exception {
		iniciar_programa();
	}

}

