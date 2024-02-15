package org.michalbaran.states;

import org.michalbaran.components.Cube;
import org.michalbaran.components.Game;

public class TakeTurnState implements State {
    @Override
    public void execute(Game game) {
        game.chooseSymbol();
        game.setCoordinates();
        game.setCubeInSpot();

        if (game.getCurrentCube() != null && game.getCurrentCube().equals(new Cube("EMP,EMP,EMP,EMP,EMP,EMP"))) {
            game.setEmptySymbol();
            System.out.println("You placed the cube in the empty space. Pull out another cube for your opponent.");
            game.setCoordinates();
            game.setCubeInSpot();
        }
        game.setCurrentState(new CheckWinState());
    }

    @Override
    public String toString() {
        return "TakeTurnState";
    }
}

