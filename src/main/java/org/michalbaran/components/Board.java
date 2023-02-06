package org.michalbaran.components;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Spot> spots;

    public Board(List<Cube> cubes) {
        spots = cubes.stream()
                .map(Spot::new)
                .collect(Collectors.toList());
    }

    public void show(boolean firstSide) {
        System.out.printf("%s player board:\n | A | B | C | D | E |", firstSide ? "First" : "Second");
        for (int i = 0; i < spots.size(); i++) {
            if (i % 5 == 0) {
                System.out.printf("\n%s|", i / 5);
            }
            System.out.printf("%s|",
                    firstSide ? spots.get(i).getSymbol1() : spots.get(4 - (i % 5) + (i / 5) * 5).getSymbol2());
        }
        System.out.println();
    }
}