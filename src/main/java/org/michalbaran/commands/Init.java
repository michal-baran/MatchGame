package org.michalbaran.commands;

import org.michalbaran.Game;

public class Init extends Command {
    public Init(Game game) {
        super(game);
    }

    @Override
    public Command execute() {
        this.game.setPlayers();
        return new Show(this.game);
    }
}
