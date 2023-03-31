import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.michalbaran.components.Board;
import org.michalbaran.components.Cube;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    private List<Cube> cubes;
    private Board board;

    @BeforeEach
    public void setUp() throws IOException {
        try (Stream<String> cubesStream = Files.lines(Path.of("src/test/resources/Cubes.txt"))) {
            cubes = cubesStream.map(Cube::new).collect(Collectors.toList());
        }
        board = new Board(cubes);
    }

    @Test
    public void testGetCubeFromSpot() {
        Cube expectedCube = cubes.get(0);
        Cube actualCube = board.getCubeFromSpot(new int[]{0, 0});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(1);
        actualCube = board.getCubeFromSpot(new int[]{1, 0});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(2);
        actualCube = board.getCubeFromSpot(new int[]{2, 0});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(3);
        actualCube = board.getCubeFromSpot(new int[]{3, 0});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(4);
        actualCube = board.getCubeFromSpot(new int[]{4, 0});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(5);
        actualCube = board.getCubeFromSpot(new int[]{0, 1});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(6);
        actualCube = board.getCubeFromSpot(new int[]{1, 1});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(7);
        actualCube = board.getCubeFromSpot(new int[]{2, 1});
        assertEquals(expectedCube, actualCube);

        expectedCube = cubes.get(8);
        actualCube = board.getCubeFromSpot(new int[]{3, 1});
        assertEquals(expectedCube, actualCube);
    }
}