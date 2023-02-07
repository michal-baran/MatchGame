package org.michalbaran;

import org.michalbaran.components.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("src/main/resources/Cubes.txt");
        game.play();
    }
}