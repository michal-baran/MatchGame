package org.michalbaran;

import org.michalbaran.components.Board;
import org.michalbaran.components.Cube;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Cube> cubes;
    private Board board;

    public Game(String input) {
        try {
            cubes = Files.lines(Path.of(input))
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        this.board = new Board(cubes);
    }

    public void play() {
        board.show(true);
        board.show(false);
    }
}