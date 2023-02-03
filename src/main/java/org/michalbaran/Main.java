package org.michalbaran;

import org.michalbaran.components.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board("src/main/resources/Cubes.txt");
        board.showSide1();
    }
}