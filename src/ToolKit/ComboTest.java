
package ToolKit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComboTest {

    private Combo combo;

    @Before
    public void setUp() {
        combo = new Combo("Combo 1", 0.2, "Papas", "Gaseosa", 100);
    }

    @Test
    public void testAgregarItemACombo() {
        ProductoMenu productoMenu = new ProductoMenu("Producto 1", 50);
        combo.agregaritemACombo(productoMenu);

        Assert.assertEquals(1, combo.adicionesCombo.size());
        Assert.assertEquals(productoMenu, combo.adicionesCombo.get(0));
    }

    @Test
    public void testGenerarTextoFactura() {
        String expected = "\nCombo 1 100";
        String actual = combo.generarTextoFactura();

        Assert.assertEquals(expected, actual);
    }
}


