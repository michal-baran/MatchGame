package org.michalbaran.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cube {
    private final List<Symbol> symbolsList;
    private final Map<Symbol, Symbol> opposites = new HashMap<>();

    public Cube(String input) {
        symbolsList = Stream
                .of(input.split(","))
                .map(Symbol::valueOf)
                .unordered()
                .collect(Collectors.toList());
        generateOpposites();
    }

    private void generateOpposites() {
        for (int i = 0; i < 3; i++) {
            opposites.put(symbolsList.get(i), symbolsList.get(i + 3));
            opposites.put(symbolsList.get(i + 3), symbolsList.get(i));
        }
    }

    public Symbol getOppositeSymbol(Symbol symbol) {
        return opposites.get(symbol);
    }

    public Symbol getSymbol(int index) {
        return symbolsList.get(index);
    }
}