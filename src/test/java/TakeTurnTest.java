import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.commands.CheckWin;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.TakeTurn;
import org.michalbaran.components.Game;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TakeTurnTest {
    private Game game;
    private TakeTurn command;

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        command = new TakeTurn(game);
    }

    @Test
    public void testExecute() {
        Command result = command.execute();

        // Verify that chooseSymbol(), setCoordinates() and setCubeInSpot() methods were called on game object
        verify(game, times(1)).chooseSymbol();
        verify(game, times(1)).setCoordinates();
        verify(game, times(1)).setCubeInSpot();

        // Verify that the result of execute() is an instance of TakeTurn class with the same game object
        assert result instanceof CheckWin;
    }
}
