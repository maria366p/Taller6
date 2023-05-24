package ToolKit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RestauranteTest {

    private Restaurante restaurante;

    @Before
    public void setUp() {
        restaurante = new Restaurante();
    }


    @Test
    public void testIniciarPedido() {
        restaurante.iniciarpedido("John Doe", "123 Main St");

        Pedido pedido = restaurante.getPedidoEnCurso();

        // Verificar que se haya iniciado un pedido correctamente
        Assert.assertNotNull(pedido);
        Assert.assertEquals("John Doe", pedido.getNombreCliente());
        Assert.assertEquals("123 Main St", pedido.getDireccionCliente());
    }

    @Test
    public void testCrearProductoAjustado() {
        ProductoMenu productoBase = new ProductoMenu("Hamburguesa", 100);

        restaurante.crearProductoAjustado(productoBase);
        ProductoAjustado productoAjustado = restaurante.getProductoAjustadoActual();

        // Verificar que se haya creado un producto ajustado correctamente
        Assert.assertNotNull(productoAjustado);
        Assert.assertEquals(productoBase, productoAjustado.getProductoBase());
        Assert.assertTrue(productoAjustado.getExtras().isEmpty());
        Assert.assertTrue(productoAjustado.getRemoved().isEmpty());
    }



}
