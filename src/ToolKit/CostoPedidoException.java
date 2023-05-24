package ToolKit;

public class CostoPedidoException extends Exception{
    public CostoPedidoException (String mensaje){
        super("No se puede agregar el producto, porque la cuenta costaría " + mensaje);

    }
}
