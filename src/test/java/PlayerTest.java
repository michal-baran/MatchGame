import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Player;
import org.michalbaran.enums.Symbol;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("John");
    }

    @Test
    public void testNewPlayer() {
        assertNotNull(player);
        assertEquals("John", player.getName());
        assertNull(player.getCards());
        assertEquals(0, player.getPoints());
    }

    @Test
    public void testGetName() {
        assertEquals("John", player.getName());
    }

    @Test
    public void testAddPoints() {
        player.addPoints(1);
        assertEquals(3, player.getPoints());
        player.addPoints(5);
        assertEquals(8, player.getPoints());
    }

    @Test
    public void testSetCards() {
        player.setCards(Symbol.HED, Symbol.TRE);
        Set<Symbol> expected = Set.of(Symbol.HED, Symbol.TRE);
        assertEquals(expected, player.getCards());
    }
}