package org.michalbaran.components;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Spot>> spots = new ArrayList<>();

    public Board(List<Cube> cubes) {
        for (int r = 0; r < 5; r++) {
            List<Spot> spotsRow = new ArrayList<>();
            for (int c = 0; c < 5; c++) {
                spotsRow.add(new Spot(cubes.get(r * 5 + c)));
            }
            spots.add(spotsRow);
        }
    }

    public void show(boolean firstSide) {
        int row = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(firstSide ? "First" : "Second");
        sb.append(" player board:\n | A | B | C | D | E |\n");
        for (List<Spot> spotsRow : spots) {
            sb.append(++row);
            for (Spot spot : spotsRow) {
                sb.append("|").append(firstSide ? spot.getSymbol1() : spot.getSymbol2());
            }
            sb.append("|\n");
        }
        System.out.println(sb);
    }

    public Cube getCubeFromSpot(String coordinate) {
        char[] chars = coordinate.toUpperCase().toCharArray();
        int column = chars[0] - 65;
        int row = chars[1] - 49;

        return spots
                .get(row)
                .get(column)
                .getCube();
    }

    public void setCubeInSpot(String coordinate, Cube cube, Symbol symbol, boolean firstPlayer) {
        char[] chars = coordinate.toUpperCase().toCharArray();
        int column = chars[0] - 65;
        int row = chars[1] - 49;

        Spot spot = spots.get(row).get(column);
        spot.setCube(cube);

        if (firstPlayer) {
            spot.setSymbol1(symbol);
            spot.setSymbol2(cube.getOppositeSymbol(symbol));
        } else {
            spot.setSymbol2(symbol);
            spot.setSymbol1(cube.getOppositeSymbol(symbol));
        }
    }
}