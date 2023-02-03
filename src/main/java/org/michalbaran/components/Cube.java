package org.michalbaran.components;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Cube {
    private final List<Symbol> symbolsList;
    private final Map<Symbol, Symbol> opposites = new HashMap<>();
    private Symbol side1;    // symbol visible on the first player side
    private Symbol side2;    // symbol visible on the second player side

    public Cube(String input) {
        symbolsList = Stream
                .of(input.split(","))
                .map(Symbol::valueOf)
                .collect(Collectors.toList());

        generateOppositeMap();
        changeSymbol(symbolsList.get(0));
    }

    private void generateOppositeMap() {
        for (int i = 0; i < 3; i++) {
            opposites.put(symbolsList.get(i), symbolsList.get(i + 3));
            opposites.put(symbolsList.get(i + 3), symbolsList.get(i));
        }
    }

    public void changeSymbol(Symbol symbol){
        side1 = symbol;
        side2 = opposites.get(symbol);
    }
}