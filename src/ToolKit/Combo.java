package ToolKit;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto{
	
 private double descuento;
 private String nombreCombo;
 private String nombreComboPeq;
 private String papas;
 private String gaseosa;
 private int preciobase;
 
 
 public List<ProductoMenu> adicionesCombo = new ArrayList<ProductoMenu>();
 
 
 
 	//************************************************************************
	// Constructor
	// ************************************************************************
 
 
	//NOTA: Se cambió la entrada de los atributos para el método, original recibe dos, mío recibe 5.
 	//      Se requería guardar los productos que se incluían dentro del combo, Dado que los atributos 
 	//      son características del objeto , no se pueden olvidar estas caracterísiticas, se deben incluir
 
 	public Combo(String unCombo, double elDescuento, String Papas, String Gaseosa, int preciobase)
 		{
 			this.descuento= elDescuento;
 			this.nombreCombo= unCombo;
 			//Otros atributos, productos que se incluyen en el combo
 			this.papas = Papas;
 			this.gaseosa = Gaseosa;
 			this.preciobase = preciobase;
 			
 		}
 	
	//************************************************************************
	// Métodos
	// ************************************************************************
 	
 	public void agregaritemACombo(ProductoMenu itemCombo) {
 		adicionesCombo.add(itemCombo);
 		
 	}


	//Getters
	public int getPrecio(){
		double valordescuento = preciobase*descuento;
		int preciofinal = (int) Math.round(preciobase - valordescuento);
		
		/*if (adicionesCombo.size() != 0) {
			for (ProductoMenu elem: adicionesCombo) {
				preciofinal += elem.getPrecio();
			}
		}*/
		
		return preciofinal;
	}

	public String getNombre() {
		return nombreCombo;
	}

	public String generarTextoFactura() {
		
		//retorna un string con la factura correspondiente a este combo
		String stPrecio= Integer.toString(preciobase);
		String StringFactura= "\n"+ nombreCombo+ " "+stPrecio;//formato (nombrecombo precio
			
	
		return StringFactura;
	}
}

