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
        Cube cube = this.game.getCubeInHand();

        System.out.printf("Choose symbol from cube: %s\n", cube);
        Symbol symbol = Symbol.valueOf(this.game.getSc().next().toUpperCase());

        System.out.printf("Choose spot for symbol: %s\n", symbol);
        String coord = game.getSc().next();
        game.setCubeInHand(coord);

        this.game.getBoard().setCubeInSpot(coord, cube, symbol, game.isFirstPlayerTurn());
        return new Show(game);
    }
}
