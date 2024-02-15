import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Cube;
import org.michalbaran.components.Game;
import org.michalbaran.states.CheckWinState;
import org.michalbaran.states.State;
import org.michalbaran.states.TakeTurnState;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TakeTurnStateTest {

    private Game game;
    private State takeTurnState;

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        takeTurnState = new TakeTurnState();
    }

    @Test
    public void testExecute() {
        // Wywołanie metody execute
        takeTurnState.execute(game);

        // Sprawdzenie, czy chooseSymbol(), setCoordinates() i setCubeInSpot() zostały wywołane raz
        verify(game, times(1)).chooseSymbol();
        verify(game, times(1)).setCoordinates();
        verify(game, times(1)).setCubeInSpot();

        // Sprawdzenie, czy setEmptySymbol(), setCoordinates() i setCubeInSpot() są wywoływane, jeśli currentCube jest pusty
        when(game.getCurrentCube()).thenReturn(new Cube("EMP,EMP,EMP,EMP,EMP,EMP"));
        takeTurnState.execute(game);
        verify(game, times(1)).setEmptySymbol();
        verify(game, times(3)).setCoordinates(); // wywołane raz przed setEmptySymbol() i raz po nim
        verify(game, times(3)).setCubeInSpot(); // wywołane raz przed setEmptySymbol() i raz po nim

        // Sprawdzenie, czy setCurrentState jest wywołane z CheckWinState
        verify(game, times(2)).setCurrentState(Mockito.any(CheckWinState.class));
    }
}