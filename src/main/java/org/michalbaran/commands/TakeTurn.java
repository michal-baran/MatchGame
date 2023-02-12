package org.michalbaran.commands;

import org.michalbaran.components.Game;
import org.michalbaran.components.Cube;
import org.michalbaran.enums.Symbol;

public class TakeTurn extends Command {
    public TakeTurn(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        Cube cube = game.getCubeInHand();
        Symbol symbol;

        while (true) {
            System.out.printf("%s player turn: Choose a symbol from the cube: %s\n", game.getActPlayer().getName(), cube);
            try {
                symbol = Symbol.valueOf(game.getInput());
                cube.isSymbolPresent(symbol);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("There is no such symbol!");
            }
        }
        System.out.printf("Type coordinates for symbol %s\n", symbol);
        game.setCoordinates();
        game.setCubeInSpot(symbol);
        game.switchPlayers();

        return new CheckWin(game);
    }
}