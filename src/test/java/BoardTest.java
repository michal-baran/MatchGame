import org.junit.jupiter.api.BeforeEach;
import org.michalbaran.components.Board;
import org.michalbaran.components.Cube;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        try (Stream<String> cubesStream = Files.lines(Path.of("src/main/resources/Cubes.txt"))) {
            List<Cube> cubes = cubesStream
                    .map(Cube::new)
                    .collect(Collectors.toList());
            board = new Board(cubes);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}