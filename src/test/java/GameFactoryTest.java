import org.junit.jupiter.api.Test;
import org.michalbaran.components.Game;
import org.michalbaran.components.GameFactory;

class GameFactoryTest {
    private GameFactory gameFactory;

    @Test
    void createGame() {
        assert GameFactory.createGame() instanceof Game;
    }
}