package org.michalbaran.states;

import org.michalbaran.components.Game;

public class ShowState implements State {

    @Override
    public void execute(Game game) {
        game.showBoard();
        game.setCurrentState(new TakeTurnState());
    }

    @Override
    public String toString() {
        return "ShowState";
    }
}