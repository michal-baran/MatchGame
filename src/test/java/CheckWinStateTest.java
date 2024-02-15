import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Game;
import org.michalbaran.states.CheckWinState;
import org.michalbaran.states.ShowState;

import static org.mockito.Mockito.*;

public class CheckWinStateTest {
    private Game game;
    private CheckWinState state;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        state = new CheckWinState();
    }

    @Test
    public void testExecute() {
        state.execute(game);

        // Verify that checkMatch() method was called on game object
        verify(game, times(1)).checkMatch();

        // Verify that the result of execute() is an instance of ShowState class with the same game object
        verify(game, times(1)).setCurrentState(isA(ShowState.class));
    }
}
