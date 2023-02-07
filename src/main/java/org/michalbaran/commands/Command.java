package org.michalbaran.commands;

import org.michalbaran.Game;

public abstract class Command {
    Game game;

    public Command(Game game) {
        this.game = game;
    }

    public abstract Command execute();
}
