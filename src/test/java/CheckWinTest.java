import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.commands.CheckWin;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;
import org.michalbaran.components.Game;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CheckWinTest {
    private Game game;
    private CheckWin command;

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        command = new CheckWin(game);
    }

    @Test
    public void testExecute() {
        Command result = command.execute();

        // Verify that checkMatch() and switchPlayers() methods were called on game object
        verify(game, times(1)).checkMatch();
        verify(game, times(1)).switchPlayers();

        // Verify that the result of execute() is an instance of Show class with the same game object
        assert result instanceof Show;
    }
}