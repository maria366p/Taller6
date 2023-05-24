package ToolKit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestProductoMenu {
    private ProductoMenu productoMenu;

    @Before
    public void setUp() {
        productoMenu = new ProductoMenu("Hamburguesa", 10);
    }

    @Test
    public void testGenerarTextoFactura() {
        String expectedText = "Hamburguesa  10";
        Assert.assertEquals(expectedText, productoMenu.generarTextoFactura());
    }
}

