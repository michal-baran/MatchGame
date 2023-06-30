import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.commands.Show;
import org.michalbaran.components.Game;

public class GameTest {
    private Game game;


    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testNewGame() {
        Assertions.assertNotNull(game.getCubes());
        Assertions.assertNotNull(game.getBoard());
        Assertions.assertNotNull(game.getCards());
        assert game.getActCommand() instanceof Show;
    }
}