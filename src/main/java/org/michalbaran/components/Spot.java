package org.michalbaran.components;

import org.michalbaran.enums.Symbol;

import java.util.Random;

public class Spot {
    private Cube cube;
    private Symbol symbol1;
    private Symbol symbol2;

    public Spot(Cube cube) {
        this.cube = cube;
        this.symbol1 = cube.getSymbol(new Random().nextInt(6));
        this.symbol2 = cube.getOppositeSymbol(symbol1);
    }

    public void setSymbol(Symbol symbol, boolean firstPlayerTurn) {
        if (firstPlayerTurn) {
            symbol1 = symbol;
            symbol2 = cube.getOppositeSymbol(symbol);
        } else {
            symbol2 = symbol;
            symbol1 = cube.getOppositeSymbol(symbol);
        }
    }

    public Symbol getSymbol(boolean firstPlayerTurn) {
        return firstPlayerTurn ? symbol1 : symbol2;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    public Cube getCube(){
        return cube;
    }
}