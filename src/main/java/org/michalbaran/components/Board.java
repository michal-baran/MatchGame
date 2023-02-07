package org.michalbaran.components;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Spot>> spots = new ArrayList<>();

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

    public boolean verifyInitState() {
        for (List<Spot> spot : spots) {
            for (int c = 0; c < spots.get(0).size(); c++) {
                if (spot.get(c) == spot.get(c + 1)) {
                    System.out.println("Board is not prepared correctly");
                    return true;
                }
            }
        }
        return false;
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

        Spot tempSpot = spots.get(row).get(column);
        tempSpot.setCube(cube);
        if (firstPlayer) {
            tempSpot.setSymbol1(symbol);
        } else {
            tempSpot.setSymbol2(symbol);
        }
    }
}