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
        ensureUniqueness();
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

    public void ensureUniqueness() {
        // należy sprawdzić pole i porównać sąsiadujące pola po prawej, na dole i po prawej na dole
        for (int row = 0; row < spots.size(); row++) {
            for (int col = 0; col < spots.get(row).size(); col++) {
                Spot spot = spots.get(row).get(col);
                Symbol checked1 = spot.getSymbol(true);   // sprawdzany symbol dla strony 1
                Symbol checked2 = spot.getSymbol(false);   // sprawdzany symbol dla strony 2
                List<Symbol> symbols1 = new ArrayList<>();
                List<Symbol> symbols2 = new ArrayList<>();

                if (row > 0) {
                    Symbol onTop = spots.get(row - 1).get(col).getSymbol(true);  //sprawdzenie pola u góry dla tablicy gracza 1
                    symbols1.add(onTop);
                    onTop = spots.get(row - 1).get(col).getSymbol(false);  //sprawdzenie pola u góry dla tablicy gracza 2
                    symbols2.add(onTop);
                    if (col < 4) {
                        Symbol onTopRight = spots.get(row - 1).get(col + 1).getSymbol(true);  //sprawdzenie pola u góry po prawej dla tablicy gracza 1
                        symbols1.add(onTopRight);
                        onTopRight = spots.get(row - 1).get(col + 1).getSymbol(true);  //sprawdzenie pola u góry po prawej dla tablicy gracza 2
                        symbols2.add(onTopRight);
                    }
                }
                if (col < 4) {
                    Symbol onRight = spots.get(row).get(col + 1).getSymbol(true);   //sprawdzenie pola po prawej dla tablicy gracza 1
                    symbols1.add(onRight);
                    onRight = spots.get(row).get(col + 1).getSymbol(false);   //sprawdzenie pola po prawej dla tablicy gracza 2
                    symbols2.add(onRight);
                    if (row < 4) {
                        Symbol onBottomRight = spots.get(row + 1).get(col + 1).getSymbol(true); //sprawdzenie pola na dole po prawej dla tablicy gracza 1
                        symbols1.add(onBottomRight);
                        onBottomRight = spots.get(row + 1).get(col + 1).getSymbol(false); //sprawdzenie pola na dole po prawej dla tablicy gracza 2
                        symbols2.add(onBottomRight);
                    }
                }
                if (row < 4) {
                    Symbol onBottom = spots.get(row + 1).get(col).getSymbol(true);  //sprawdzenie pola na dole dla tablicy gracza 1
                    symbols1.add(onBottom);
                    onBottom = spots.get(row + 1).get(col).getSymbol(false);  //sprawdzenie pola na dole dla tablicy gracza 2
                    symbols2.add(onBottom);
                }

                boolean notUnique = symbols1.contains(checked1) || symbols2.contains(checked2);
                while (notUnique) {
                    spot.setToNextSymbol();
                    checked1 = spot.getSymbol(true);
                    checked2 = spot.getSymbol(false);
                    notUnique = symbols1.contains(checked1) || symbols2.contains(checked2);
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
                        .get(4 - i)
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