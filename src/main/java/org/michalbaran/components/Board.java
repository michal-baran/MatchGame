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
        emptyCube = new Cube("EMP,EMP,EMP,EMP,EMP,EMP");
        ensureUniqueness(true);
    }

    public Cube getCubeFromSpot(int[] coords) {
        Cube cube = spots
                .get(coords[1])
                .get(coords[0])
                .getCube();

        // set empty cube in spot
        setCubeInSpot(coords, emptyCube, Symbol.EMP, true);
        return cube;
    }

    public void setCubeInSpot(int[] coords, Cube cube, Symbol symbol, boolean firstPlayerTurn) {
        Spot spot = spots.get(coords[1]).get(coords[0]);
        spot.setCube(cube);
        spot.setSymbol(symbol, firstPlayerTurn);

    }

    public void ensureUniqueness(boolean side) {
        // należy sprawdzić pole i porównać sąsiadujące pola po prawej, na dole i po prawej na dole
        for (int row = 0; row < spots.size(); row++) {
            for (int col = 0; col < spots.get(row).size(); col++) {
                Spot spot = spots.get(row).get(col);
                Symbol checked = spot.getSymbol(side);   // sprawdzany symbol
                List<Symbol> symbols = new ArrayList<>();

                if (row > 0) {
                    Symbol onTop = spots.get(row - 1).get(col).getSymbol(side);  //sprawdzenie pola u góry
                    symbols.add(onTop);
                    if (col < 4) {
                        Symbol onTopRight = spots.get(row - 1).get(col + 1).getSymbol(side);  //sprawdzenie pola u góry po prawej
                        symbols.add(onTopRight);
                    }
                }
                if (col < 4) {
                    Symbol onRight = spots.get(row).get(col + 1).getSymbol(side);   //sprawdzenie pola po prawej
                    symbols.add(onRight);
                    if (row < 4) {
                        Symbol onBottomRight = spots.get(row + 1).get(col + 1).getSymbol(side); //sprawdzenie pola na dole po prawej
                        symbols.add(onBottomRight);
                    }
                }
                if (row < 4) {
                    Symbol onBottom = spots.get(row + 1).get(col).getSymbol(side);  //sprawdzenie pola na dole
                    symbols.add(onBottom);
                }
                boolean notUnique = symbols.contains(checked);
                while (notUnique) {
                    spot.setToNextSymbol();
                    checked = spot.getSymbol(side);
                    notUnique = symbols.contains(checked);
                }
            }
        }
    }

    public void show(String name, boolean firstPlayerTurn) {
        int row = 0;
        StringBuilder sb = new StringBuilder(name + "'s board:\n");
        sb.append(firstPlayerTurn ? " | A | B | C | D | E |\n" : " | E | D | C | B | A |\n");

        for (List<Spot> spotsRow : spots) {
            sb.append(++row);
            for (int i = 0; i < spotsRow.size(); i++) {
                int index = firstPlayerTurn ? i : 4 - i;
                Spot spot = spotsRow.get(index);
                sb.append("|").append(spot.getSymbol(firstPlayerTurn));
            }
            sb.append("|\n");
        }
        System.out.println(sb);
    }

    public boolean checkBoardForMatch(int[] coords, boolean firstPlayerTurn, Symbol actSymbol) {
        int[] count = new int[4];

        // check row
        for (Spot spot : spots.get(coords[1])) {
            count[0] += spot
                    .getSymbol(firstPlayerTurn)
                    .equals(actSymbol) ? 1 : 0;
        }
        // check column
        for (int i = 0; i < 5; i++) {
            count[1] += spots
                    .get(i)
                    .get(coords[0])
                    .getSymbol(firstPlayerTurn)
                    .equals(actSymbol) ? 1 : 0;
        }
        // check diagonal
        if (coords[0] == coords[1]) {
            for (int i = 0; i < 5; i++) {
                count[2] += spots
                        .get(i)
                        .get(i)
                        .getSymbol(firstPlayerTurn)
                        .equals(actSymbol) ? 1 : 0;
            }
        }
        // check counter-diagonal
        // first check if last coordinates lies on pattern
        if ((coords[1] + coords[0] - 4) == 0) {
            for (int i = 0; i < 5; i++) {
                count[3] += spots
                        .get(4-i)
                        .get(i)
                        .getSymbol(firstPlayerTurn)
                        .equals(actSymbol) ? 1 : 0;
            }
        }
        for (int c : count) {
            if (c == 5) return true;
        }
        return false;
    }
}