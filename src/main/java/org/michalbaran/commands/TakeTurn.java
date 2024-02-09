package org.michalbaran.commands;

import org.michalbaran.components.Cube;
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

        if (game.getCurrCube().equals(new Cube("EMP,EMP,EMP,EMP,EMP,EMP"))){
            game.setEmptySymbol();
            System.out.println("You placed the cube in the empty space. Pull out another cube for your opponent.");
            game.setCoordinates();
            game.setCubeInSpot();
        }
        return new CheckWin(game);
    }
}
