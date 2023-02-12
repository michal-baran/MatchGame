package org.michalbaran.commands;

import org.michalbaran.components.Game;

public class TakeTurn extends Command {
    public TakeTurn(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        game.chooseSymbol();
        game.setCoordinates();
        game.setCubeInSpot();

        return new CheckWin(game);
    }
}
