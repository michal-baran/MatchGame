package org.michalbaran.states;

import org.michalbaran.components.Game;

public class CheckWinState implements State {
    @Override
    public void execute(Game game) {
        game.checkMatch();
        game.setCurrentState(new ShowState());
    }

    @Override
    public String toString() {
        return "CheckWinState";
    }
}
