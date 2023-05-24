package ToolKit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

    private Pedido pedido;

    @Before
    public void setUp() {
        pedido = new Pedido("John Doe", "123 Main St");
    }

    @Test
    public void testAgregarProducto() throws CostoPedidoException {
        Producto producto = new Combo("Combo 1", 0.2, "Papas", "Gaseosa", 100);
        pedido.agregarProducto(producto);

        Assert.assertEquals(1, pedido.prodPedido.size());
        Assert.assertEquals(producto, pedido.prodPedido.get(0));
    }

    @Test
    public void testAgregarProductoExcedeCosto() throws CostoPedidoException {

        try{
        Producto producto1 = new ProductoMenu("Producto 1", 100000);
        Producto producto2 = new ProductoMenu("Producto 2", 150000);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
        }
        catch (CostoPedidoException e){
            Assert.assertEquals("No se puede agregar el producto, porque la cuenta costaría 250000", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testGetPrecioTotalPedido() throws CostoPedidoException {
        Producto producto1 = new ProductoMenu("Producto 1", 50);
        Producto producto2 = new ProductoMenu("Producto 2", 75);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int expected = 125;
        int actual = pedido.getPrecioTotalPedido();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPrecioNetoPedido() throws CostoPedidoException {
        Producto producto1 = new ProductoMenu("Producto 1", 50);
        Producto producto2 = new ProductoMenu("Producto 2", 75);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int expected = 149;
        int actual = pedido.getPrecioNetoPedido();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPrecioIVAPedido() throws CostoPedidoException {
        Producto producto1 = new ProductoMenu("Producto 1", 50);
        Producto producto2 = new ProductoMenu("Producto 2", 75);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        int expected = 24;
        int actual = pedido.getPrecioIVAPedido();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGenerarTextoFactura() throws CostoPedidoException {
        Producto producto1 = new ProductoMenu("Producto 1", 50);
        Producto producto2 = new ProductoMenu("Producto 2", 75);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String expected = "Producto 1  50\nProducto 2  75\n\nPrecio Total Pedido:  125\n\nPrecio IVA Pedido:  24\n\nPrecio Neto Pedido:  149\n";
        String actual = pedido.generarTextoFactura();

        Assert.assertEquals(expected, actual);
    }
}

