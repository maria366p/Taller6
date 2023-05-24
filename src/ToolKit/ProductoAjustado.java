package ToolKit;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustado implements Producto{
	
	Producto Base;
	List<Ingrediente> agregados;
	List<Ingrediente> eliminados;
	
	
	public ProductoAjustado(Producto Base, List<Ingrediente> agregados, List<Ingrediente> eliminados) {
		this.Base = Base;
		this.agregados = agregados;
		this.eliminados = eliminados; 
		
		
	}
	

	public List<Ingrediente> RemoveIngrediente(Ingrediente NoDeseado) {
		eliminados.add(NoDeseado);
		return eliminados;
	}
	
	public List<Ingrediente> AddIngrediente(Ingrediente Deseado) {
		agregados.add(Deseado);
		return agregados;
	}


	

	@Override
	public int getPrecio() {
		int Precio = Base.getPrecio(); //Precio inicial del producto
		for (Ingrediente Deseado: agregados) {
			int precioIngDeseado = Deseado.getCostoAdicional();
			Precio += precioIngDeseado;
			
		}
		return Precio;
	}

	@Override
	public String getNombre() {
		return Base.getNombre();
	}

	@Override
	public String generarTextoFactura() {
		String StringFactura = "Producto principal:\n" + Base.getNombre()+ "  " + Base.getPrecio() + "\n";
		if (agregados.size() != 0) {
			StringFactura += " Adicion de: \n" ;
			for (Ingrediente elem: agregados) {
				StringFactura = StringFactura + elem.getName() + "   " + elem.getCostoAdicional() + "\n";
			}
		}
		if (eliminados.size() != 0) {
			StringFactura += " Ingredientes eliminados: \n";
			for (Ingrediente elem: eliminados) {
				StringFactura = StringFactura + elem.getName() + "   " + elem.getCostoAdicional() + "\n";
			}
		}

			
	
		return StringFactura;
		
	}


	public List<Ingrediente> getRemoved(){
		//getter de lista de ingredientes eliminados
		return eliminados;
	}


	public List<Ingrediente> getExtras() {
		//getter de lista de ingredientes agregados
		return agregados;
	}


	public Object getProductoBase() {
		return Base;
	}
	
}
