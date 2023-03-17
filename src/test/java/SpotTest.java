import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Cube;
import org.michalbaran.components.Spot;

import static org.junit.jupiter.api.Assertions.*;

public class SpotTest {
    Spot spot;
    Cube cube = new Cube("HED,DUM,HAR,MCG,ZGR,TON");

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
}
