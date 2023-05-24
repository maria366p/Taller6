package ToolKit;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int numeroPedidos;
	public static int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	
	//Array List productos del pedido
	public List<Producto> prodPedido= new ArrayList<Producto>();
	
	
	public Pedido (String nombreCliente, String direccionCliente) {
		//this.numeroPedidos= numeroPedidos;
		idPedido ++; //Se incrementa 1 cada vez que el método es usado
		this.nombreCliente= nombreCliente;
		this.direccionCliente= direccionCliente;
	}



	
	public void agregarProducto(Producto nuevoItem) throws CostoPedidoException{
		try{
		int CostoActual = getPrecioTotalPedido();

		CostoActual += nuevoItem.getPrecio();

		if (CostoActual >= 150000){
			String CostoFuturo = Integer.toString(CostoActual);
			throw new CostoPedidoException(CostoFuturo);
		}

		prodPedido.add(nuevoItem);
		}
		catch (CostoPedidoException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	//GETTERS
	
	public int getidPedido() {
		return idPedido;
	}
	
	public int getPrecioTotalPedido() {
		int suma = 0;
		for (Producto elem: prodPedido ) {
			int precio = elem.getPrecio();
			suma += precio;
			}
		return suma;
	}
	
	public int getPrecioNetoPedido() {
		int suma = getPrecioTotalPedido();
		int iva = getPrecioIVAPedido();
		return suma + iva;
		
	}
	
	public int getPrecioIVAPedido() {
		int suma = getPrecioTotalPedido();
		double impuesto = suma*0.19;
		int iva= (int) Math.round(impuesto);
		return iva;
		
	}

	public String getNombreCliente(){
		return nombreCliente;
	}

	public String getDireccionCliente(){
		return direccionCliente;
	}
	
	
	public String generarTextoFactura() {
		String StringFactura = "";
		for (Producto elem: prodPedido) {
			StringFactura += elem.generarTextoFactura() + "\n";
		}
		
		int preciototalpedido = getPrecioTotalPedido();
		StringFactura = StringFactura + "\nPrecio Total Pedido:  "  + preciototalpedido + "\n";
		
		int precioIvaPedido = getPrecioIVAPedido();
		StringFactura = StringFactura + "\nPrecio IVA Pedido:  "  + precioIvaPedido + "\n";
		
		int precioNetoPedido =  getPrecioNetoPedido();
		
		StringFactura = StringFactura + "\nPrecio Neto Pedido:  "  + precioNetoPedido + "\n";
		System.out.println(StringFactura);		
		return StringFactura;
	}
	
	
	
	
}