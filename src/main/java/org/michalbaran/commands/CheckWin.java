package org.michalbaran.commands;

import org.michalbaran.components.Game;

public class CheckWin extends Command{

    public CheckWin(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        game.checkMatch();
        game.switchPlayers();

        return new Show(this.game);
    }
}
