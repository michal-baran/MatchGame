import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Cube;
import org.michalbaran.components.Spot;
import org.michalbaran.enums.Symbol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpotTest {
    private Spot spot;
    private final Cube cube = new Cube("HED,DUM,HAR,MCG,ZGR,TON");

    @BeforeEach
    void setUp() {
        spot = new Spot(cube);
    }

    @Test
    public void testNewSpot() {
        assertNotNull(spot.getCube());
        assertNotNull(spot.getSymbol(true));
        assertNotNull(spot.getSymbol(false));
    }

    @Test
    public void testGetCube() {
        assertEquals(cube, spot.getCube());
    }

    @Test
    public void testSetCube() {
        Cube newCube = new Cube("DRA,HED,RON,TON,ZGR,LUN");
        spot.setCube(newCube);
        assertEquals(newCube, spot.getCube());
    }

    @Test
    public void testGetSymbol() {
        assertEquals(Symbol.HED, spot.getSymbol(true));
        assertEquals(Symbol.MCG, spot.getSymbol(false));
    }

    @Test
    public void testSetSymbol() {
        spot.setSymbol(Symbol.HAR, true);
        assertEquals(Symbol.HAR, spot.getSymbol(true));
        assertEquals(Symbol.TON, spot.getSymbol(false));

        spot.setSymbol(Symbol.MCG, false);
        assertEquals(Symbol.HED, spot.getSymbol(true));
        assertEquals(Symbol.MCG, spot.getSymbol(false));
    }

    @Test
    public void testSetToNextSymbol() {
        spot.setToNextSymbol();
        assertEquals(Symbol.DUM, spot.getSymbol(true));
        assertEquals(Symbol.ZGR, spot.getSymbol(false));

        spot.setToNextSymbol();
        assertEquals(Symbol.HAR, spot.getSymbol(true));
        assertEquals(Symbol.TON, spot.getSymbol(false));

        spot.setToNextSymbol();
        assertEquals(Symbol.MCG, spot.getSymbol(true));
        assertEquals(Symbol.HED, spot.getSymbol(false));

        spot.setToNextSymbol();
        assertEquals(Symbol.ZGR, spot.getSymbol(true));
        assertEquals(Symbol.DUM, spot.getSymbol(false));

        spot.setToNextSymbol();
        assertEquals(Symbol.TON, spot.getSymbol(true));
        assertEquals(Symbol.HAR, spot.getSymbol(false));

        spot.setToNextSymbol();
        assertEquals(Symbol.HED, spot.getSymbol(true));
        assertEquals(Symbol.MCG, spot.getSymbol(false));
    }
}