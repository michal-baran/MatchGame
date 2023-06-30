package org.michalbaran.commands;

import org.michalbaran.components.Game;

public abstract class Command {
    final Game game;

    public Command(Game game) {
        this.game = game;
    }
    public abstract Command execute();
}
