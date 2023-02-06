package org.michalbaran;

import org.michalbaran.components.Board;
import org.michalbaran.components.Cube;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Board board;
    private List<Cube> cubes;

    public Game(String input) {
        try {
            cubes = Files.lines(Path.of(input))
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void prepareBoard() {

    }
}
