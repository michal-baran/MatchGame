package org.michalbaran.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Cube> cubes;
    private List<List<Symbol>> fields = new ArrayList<>();

    public Board(String input) {
        try {
            cubes = Files.lines(Path.of(input))
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSide1() {
        cubes.forEach(c -> System.out.println(c.getSide1()));
    }
    public void showSide2() {
        cubes.forEach(c -> System.out.println(c.getSide2()));
    }
}