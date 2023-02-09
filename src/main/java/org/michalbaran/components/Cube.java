package org.michalbaran.components;

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

    public void isSymbolPresent(Symbol symbol) {
        if (!symbolsList.contains(symbol)) throw new IllegalArgumentException();
    }

    public Symbol getSymbol(int index) {
        return symbolsList.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stream.of(symbolsList)
                .forEach(sb::append);
        return sb.toString();
    }
}