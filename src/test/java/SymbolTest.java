import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.enums.AnsiColor;
import org.michalbaran.enums.Symbol;

public class SymbolTest {
    private Symbol symbol;

    @BeforeEach
    public void setUp() {
        symbol = Symbol.DRA;
    }

    @Test
    public void testToString() {
        String expected = AnsiColor.BRIGHT_GREEN.getColorCode() + "DRA" + AnsiColor.RESET.getColorCode();
        String actual = symbol.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetColorCode() {
        Assertions.assertEquals(AnsiColor.BRIGHT_GREEN, symbol.getColor());
    }

    @Test
    public void testGetText() {
        Assertions.assertEquals("DRA", symbol.getText());
    }
}