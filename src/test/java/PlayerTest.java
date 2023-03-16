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
        player = new Player("First player");
    }

    @Test
    public void testNewPlayer() {
        Player player = new Player("John");
        assertNotNull(player);
        assertEquals("John", player.getName());
        assertNull(player.getCards());
        assertEquals(0, player.getPoints());
    }

    @Test
    public void testGetName() {
        assertEquals("First player", player.getName());
    }

    @Test
    public void testAddPoints() {
        Player player = new Player("Johhny");
        player.addPoints(5);
        assertEquals(5, player.getPoints());
        player.addPoints(3);
        assertEquals(8, player.getPoints());
    }

    @Test
    public void testSetCards() {
        Player player = new Player("John");
        player.setCards(Symbol.HED, Symbol.TRE);
        Set<Symbol> expected = Set.of(Symbol.HED, Symbol.TRE);
        assertEquals(expected, player.getCards());
    }
}