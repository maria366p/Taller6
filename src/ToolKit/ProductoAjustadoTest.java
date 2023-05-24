package ToolKit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductoAjustadoTest {

    private Producto base;
    private List<Ingrediente> agregados;
    private List<Ingrediente> eliminados;

    @Before
    public void setUp() {
        // Configuración inicial para las pruebas
        base = new Producto() {
            @Override
            public int getPrecio() {
                return 100; // Precio base para las pruebas
            }

            @Override
            public String getNombre() {
                return "Producto Base"; // Nombre base para las pruebas
            }

            @Override
            public String generarTextoFactura() {
                return null; // No es necesario para las pruebas
            }
        };

        agregados = new ArrayList<>();
        eliminados = new ArrayList<>();
    }

    @Test
    public void testGetPrecio() {
        // Crear un objeto ProductoAjustado para realizar las pruebas
        ProductoAjustado producto = new ProductoAjustado(base, agregados, eliminados);

        // Agregar ingredientes agregados
        Ingrediente ingrediente1 = new Ingrediente("Ingrediente 1", 20);
        Ingrediente ingrediente2 = new Ingrediente("Ingrediente 2", 30);
        agregados.add(ingrediente1);
        agregados.add(ingrediente2);

        // Probar el método getPrecio() y verificar el resultado esperado
        int precio = producto.getPrecio();
        Assert.assertEquals(150, precio);
    }

    @Test
    public void testGenerarTextoFactura() {
        // Crear un objeto ProductoAjustado para realizar las pruebas
        ProductoAjustado producto = new ProductoAjustado(base, agregados, eliminados);

        // Agregar ingredientes agregados
        Ingrediente ingrediente1 = new Ingrediente("Ingrediente 1", 20);
        agregados.add(ingrediente1);

        // Agregar ingredientes eliminados
        Ingrediente ingrediente2 = new Ingrediente("Ingrediente 2", 30);
        eliminados.add(ingrediente2);

        // Probar el método generarTextoFactura() y verificar el resultado esperado
        String textoFactura = producto.generarTextoFactura();
        String expectedText = "Producto principal:\n" +
                "Producto Base  100\n" +
                " Adicion de: \n" +
                "Ingrediente 1   20\n" +
                " Ingredientes eliminados: \n" +
                "Ingrediente 2   30\n";
        Assert.assertEquals(expectedText, textoFactura);
    }

    

}

