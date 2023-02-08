package org.michalbaran.components;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Spot {
    private Cube cube;
    private Symbol symbol1;
    private Symbol symbol2;

    public Spot(Cube cube) {
        this.cube = cube;
        this.symbol1 = cube.getSymbol(new Random().nextInt(6));
        this.symbol2 = cube.getOppositeSymbol(symbol1);
    }
}