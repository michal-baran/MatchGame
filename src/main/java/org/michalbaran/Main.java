package org.michalbaran;

import org.michalbaran.components.Game;
import org.michalbaran.components.GameFactory;

public class Main {
    public static void main(String[] args) {
        // Game initialization
        Game game = GameFactory.createGame();

        // Set players
        game.setPlayers();

        // Game start
        game.play();
    }
}