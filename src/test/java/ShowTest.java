import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;
import org.michalbaran.commands.TakeTurn;
import org.michalbaran.components.Game;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ShowTest {
    private Game game;
    private Show command;

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        command = new Show(game);
    }

    @Test
    public void testExecute() {
        Command result = command.execute();

        // Verify that showBoard() method was called on game object
        verify(game, times(1)).showBoard();

        // Verify that the result of execute() is an instance of TakeTurn class with the same game object
        assert result instanceof TakeTurn;
    }
}
