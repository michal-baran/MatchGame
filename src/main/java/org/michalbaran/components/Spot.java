package org.michalbaran.components;

import lombok.Getter;

@Getter
public class Spot {
    private Cube cube;
    private Symbol symbol1;
    private Symbol symbol2;

    public Spot(Cube cube) {
        this.cube = cube;
        setSymbol(cube.getSymbol(0), false);
    }

    public void setSymbol(Symbol symbol, boolean firstSide) {
        if (firstSide) {
            symbol1 = symbol;
            symbol2 = cube.getOppositeSymbol(symbol);
        } else {
            symbol2 = symbol;
            symbol1 = cube.getOppositeSymbol(symbol);
        }
    }
}