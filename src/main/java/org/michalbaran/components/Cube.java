package org.michalbaran.components;

import org.michalbaran.enums.Symbol;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cube {
    private final List<Symbol> symbolsList;
    private final Map<Symbol, Symbol> oppositesMap = new HashMap<>();

    public Cube(String input) {
        symbolsList = Stream
                .of(input.split(","))
                .map(Symbol::valueOf)
                .collect(Collectors.toList());
        setOpposites();
    }

    private void setOpposites() {
        for (int i = 0; i < 3; i++) {
            oppositesMap.put(symbolsList.get(i), symbolsList.get(i + 3));
            oppositesMap.put(symbolsList.get(i + 3), symbolsList.get(i));
        }
    }

    public Symbol getOppositeSymbol(Symbol symbol) {
        return oppositesMap.get(symbol);
    }

    public boolean isSymbolPresent(Symbol symbol) {
        return symbolsList.contains(symbol);
    }

    public Symbol getSymbol(int index) {
        return symbolsList.get(index);
    }

    public Symbol switchToNextSymbol(Symbol symbol) {
        int index = symbolsList.indexOf(symbol);
        return symbolsList.get((index + 1) % 5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stream.of(symbolsList)
                .forEach(sb::append);
        return sb.toString();
    }
}