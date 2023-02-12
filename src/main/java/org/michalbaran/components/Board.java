package org.michalbaran.components;

import org.michalbaran.enums.Symbol;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Spot>> spots = new ArrayList<>();
    private final Cube emptyCube;


    public Board(List<Cube> cubes) {
        for (int r = 0; r < 5; r++) {
            List<Spot> spotsRow = new ArrayList<>();
            for (int c = 0; c < 5; c++) {
                spotsRow.add(new Spot(cubes.get(r * 5 + c)));
            }
            spots.add(spotsRow);
        }
        emptyCube = cubes.get(cubes.size() - 1);
    }

    public void show(boolean firstPlayerTurn) {
        int row = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(firstPlayerTurn ? "First" : "Second");
        sb.append(" player board:\n | A | B | C | D | E |\n");
        for (List<Spot> spotsRow : spots) {
            sb.append(++row);
            for (Spot spot : spotsRow) {
                sb.append("|").append(firstPlayerTurn ? spot.getSymbol1() : spot.getSymbol2());
            }
            sb.append("|\n");
        }
        System.out.println(sb);
    }

    public Cube getCubeFromSpot(int[] coords) {
        Cube tempCube = spots
                .get(coords[1])
                .get(coords[0])
                .getCube();
        setCubeInSpot(coords, emptyCube, Symbol.EMP, true);
        return tempCube;
    }

    public void setCubeInSpot(int[] coords, Cube cube, Symbol symbol, boolean firstPlayerTurn) {
        Spot spot = spots.get(coords[1]).get(coords[0]);
        spot.setCube(cube);

        if (firstPlayerTurn) {
            spot.setSymbol1(symbol);
            spot.setSymbol2(cube.getOppositeSymbol(symbol));
        } else {
            spot.setSymbol2(symbol);
            spot.setSymbol1(cube.getOppositeSymbol(symbol));
        }
    }

    public boolean checkBoardForMatch(int[] coords, boolean firstPlayerTurn) {
        // check row

        // check column

        // check diagonal

        // check antidiagonal

        return false;
    }
}