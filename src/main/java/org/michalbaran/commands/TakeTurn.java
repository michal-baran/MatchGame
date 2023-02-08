package org.michalbaran.commands;

import org.michalbaran.components.Game;
import org.michalbaran.components.Cube;
import org.michalbaran.components.Symbol;

public class TakeTurn extends Command {
    public TakeTurn(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        Cube cube = game.getCubeInHand();
        Symbol symbol;

        while (true) {
            System.out.printf("%s player turn: Choose a symbol from the cube: %s", game.getActPlayer().getName(), cube);
            symbol = Symbol.valueOf(game.getSc().next().toUpperCase());
            if (cube.isSymbolPresent(symbol)) {
                break;
            }
            System.out.println("Symbol doesn't exist on the present cube!");
        }
        System.out.printf("Choose a spot for the symbol: %s\n", symbol);
        String coord = game.getSc().next();
        game.setCubeInHand(coord);
        game.getBoard().setCubeInSpot(coord, cube, symbol, game.isFirstPlayerTurn());

        game.switchPlayers();
        return new Show(game);
    }
}
