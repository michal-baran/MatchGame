import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Cube;
import org.michalbaran.enums.Symbol;

import static org.junit.jupiter.api.Assertions.*;

public class CubeTest {
    Cube cube;

    @BeforeEach
    void setUp() {
        cube = new Cube("HED,DUM,HAR,MCG,ZGR,TON");
    }

    @Test
    public void testGetSymbol() {
        assertEquals(Symbol.HED, cube.getSymbol(0));
        assertEquals(Symbol.DUM, cube.getSymbol(1));
        assertEquals(Symbol.HAR, cube.getSymbol(2));
        assertEquals(Symbol.MCG, cube.getSymbol(3));
        assertEquals(Symbol.ZGR, cube.getSymbol(4));
        assertEquals(Symbol.TON, cube.getSymbol(5));
    }

    @Test
    public void testIsSymbolPresent() {
        assertTrue(cube.isSymbolPresent(Symbol.HED));
        assertTrue(cube.isSymbolPresent(Symbol.DUM));
        assertTrue(cube.isSymbolPresent(Symbol.HAR));
        assertTrue(cube.isSymbolPresent(Symbol.MCG));
        assertTrue(cube.isSymbolPresent(Symbol.ZGR));
        assertTrue(cube.isSymbolPresent(Symbol.TON));

        assertFalse(cube.isSymbolPresent(Symbol.SYR));
        assertFalse(cube.isSymbolPresent(Symbol.HAG));
        assertFalse(cube.isSymbolPresent(Symbol.FLE));
        assertFalse(cube.isSymbolPresent(Symbol.LUN));
        assertFalse(cube.isSymbolPresent(Symbol.GRY));
        assertFalse(cube.isSymbolPresent(Symbol.DRA));
    }

    @Test
    public void testSwitchToNextSymbol() {
        assertEquals(Symbol.DUM, cube.switchToNextSymbol(Symbol.HED));
        assertEquals(Symbol.HAR, cube.switchToNextSymbol(Symbol.DUM));
        assertEquals(Symbol.MCG, cube.switchToNextSymbol(Symbol.HAR));
        assertEquals(Symbol.ZGR, cube.switchToNextSymbol(Symbol.MCG));
        assertEquals(Symbol.TON, cube.switchToNextSymbol(Symbol.ZGR));
        assertEquals(Symbol.HED, cube.switchToNextSymbol(Symbol.TON));
    }

    @Test
    public void testSetOppositeSymbol() {
        assertEquals(Symbol.MCG, cube.getOppositeSymbol(Symbol.HED));
        assertEquals(Symbol.ZGR, cube.getOppositeSymbol(Symbol.DUM));
        assertEquals(Symbol.TON, cube.getOppositeSymbol(Symbol.HAR));
        assertEquals(Symbol.HED, cube.getOppositeSymbol(Symbol.MCG));
        assertEquals(Symbol.DUM, cube.getOppositeSymbol(Symbol.ZGR));
        assertEquals(Symbol.HAR, cube.getOppositeSymbol(Symbol.TON));

    }
}