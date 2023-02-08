package org.michalbaran.commands;

import org.michalbaran.components.Game;

public class Show extends Command {

    public Show(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        game.getBoard().show(game.isFirstPlayerTurn());
        return new TakeTurn(this.game);
    }
}