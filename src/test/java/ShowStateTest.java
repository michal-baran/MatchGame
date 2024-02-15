import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Game;
import org.michalbaran.states.ShowState;
import org.michalbaran.states.State;
import org.michalbaran.states.TakeTurnState;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ShowStateTest {

    private Game game;
    private State showState;

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        showState = new ShowState();
    }

    @Test
    public void testExecute() {
        // Wywołanie metody execute
        showState.execute(game);

        // Sprawdzenie, czy showBoard() zostało wywołane raz na obiekcie game
        verify(game, times(1)).showBoard();

        // Sprawdzenie, czy currentState zostało ustawione na TakeTurnState
        verify(game, times(1)).setCurrentState(Mockito.any(TakeTurnState.class));
    }
}