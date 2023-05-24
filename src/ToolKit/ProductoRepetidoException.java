package ToolKit;

public class ProductoRepetidoException extends HamburguesaException{

    public ProductoRepetidoException(String mensaje) {
        super("El " + mensaje + " ya se encuentra registrado");
    }
    
}
