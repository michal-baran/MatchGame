package org.michalbaran.components;

import org.michalbaran.enums.Symbol;

public class Spot {
    private Cube cube;
    private Symbol side1;
    private Symbol side2;

    public Spot(Cube cube) {
        this.cube = cube;
        this.side1 = cube.getSymbol(0);
        this.side2 = cube.getOppositeSymbol(side1);
    }

    public void setSymbol(Symbol symbol, boolean firstPlayerTurn) {
        if (firstPlayerTurn) {
            side1 = symbol;
            side2 = cube.getOppositeSymbol(symbol);
        } else {
            side2 = symbol;
            side1 = cube.getOppositeSymbol(symbol);
        }
    }

    public Symbol getSymbol(boolean firstPlayerTurn) {
        return firstPlayerTurn ? side1 : side2;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    public Cube getCube() {
        return cube;
    }

    public void setToNextSymbol() {
        side1 = cube.switchToNextSymbol(side1);
        side2 = cube.getOppositeSymbol(side1);
    }
}