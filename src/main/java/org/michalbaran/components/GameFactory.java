package org.michalbaran.components;

public class GameFactory {

    public static Game createGame(String path){
        return new Game(path);
    }
}
