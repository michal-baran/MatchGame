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
                sb.append("|").append(spot.getSymbol(firstPlayerTurn));
            }
            sb.append("|\n");
        }
        System.out.println(sb);
    }

    public Cube getCubeFromSpot(int[] actCoords) {
        Cube cube = spots
                .get(actCoords[1])
                .get(actCoords[0])
                .getCube();

        // set empty cube in spot
        setCubeInSpot(actCoords, emptyCube, Symbol.EMP, true);
        return cube;
    }

    public void setCubeInSpot(int[] actCoords, Cube cube, Symbol symbol, boolean firstPlayerTurn) {
        Spot spot = spots.get(actCoords[1]).get(actCoords[0]);
        spot.setCube(cube);
        spot.setSymbol(symbol, firstPlayerTurn);

    }

    public boolean checkBoardForMatch(int[] actCoords, boolean firstPlayerTurn, Symbol actSymbol) {
        int[] count = new int[4];

        // check row
        for (Spot spot : spots.get(actCoords[1])) {
            count[0] += spot
                    .getSymbol(firstPlayerTurn)
                    .equals(actSymbol) ? 1 : 0;
        }

        // check column
        for (int i = 0; i < 5; i++) {
            count[1] += spots
                    .get(i)
                    .get(actCoords[0])
                    .getSymbol(firstPlayerTurn)
                    .equals(actSymbol) ? 1 : 0;
        }

        // check diagonal
        if (actCoords[0] == actCoords[1]) {
            for (int i = 0; i < 5; i++) {
                count[2] += spots
                        .get(i)
                        .get(i)
                        .getSymbol(firstPlayerTurn)
                        .equals(actSymbol) ? 1 : 0;
            }
        }
        // check antidiagonal
        for (int c : count) {
            if (c == 5) return true;
        }
        return false;
    }
}