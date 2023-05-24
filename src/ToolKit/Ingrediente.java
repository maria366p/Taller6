package ToolKit;

public class Ingrediente {
	private String nombre;
	private Integer costoAdicional;
	//Hacer un hash map (Lechuga: Valor)
	
	public Ingrediente(String unIngrediente, Integer precio) {
		this.nombre= unIngrediente;
		this.costoAdicional= precio;}
	
	
	//Getter
	public String getName(){
		return nombre;
	}
	
	public int getCostoAdicional() {
		return costoAdicional;
		
	}
}
