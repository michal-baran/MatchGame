package org.michalbaran;

import lombok.Getter;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Init;
import org.michalbaran.components.Board;
import org.michalbaran.components.Cube;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Getter
public class Game {
    private Command actCommand;
    private List<Cube> cubes;
    private Cube cubeInHand;
    private final Board board;
    private final Player[] players = new Player[2];
    private boolean firstPlayerTurn = true;
    Scanner sc = new Scanner(System.in);

    public Game(String input) {
        try {
            cubes = Files.lines(Path.of(input))
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        this.board = new Board(cubes);
        actCommand = new Init(this);
        setCubeInHand("A1");
    }

    public void play() {
        while (true) {
            actCommand = actCommand.execute();
        }
    }

    public void setPlayers() {
        System.out.println("Type name of the first player: ");
        players[0] = new Player(sc.next());
        System.out.println("Type name of the second player: ");
        players[1] = new Player(sc.next());
    }

    public void setCubeInHand (String coord) {
        cubeInHand = getCube(coord);
    }

    public void switchPlayers() {
        this.firstPlayerTurn = !firstPlayerTurn;
    }

    public Cube getCube(String coordinates) {
        return this.board.getCubeFromSpot(coordinates);
    }
}