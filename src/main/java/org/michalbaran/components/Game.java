package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        try (Stream<String> inputStream = Files.lines(Path.of(input))) {
            cubes = inputStream
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        this.board = new Board(cubes);
        setCubeInHand("A1");
        setPlayers();
        actCommand = new Show(this);
    }

    public void play() {
        while (true) {
            actCommand = actCommand.execute();
        }
    }

    private void setPlayers() {
        System.out.println("Type name of the first player: ");
        players[0] = new Player(sc.next());
        System.out.println("Type name of the second player: ");
        players[1] = new Player(sc.next());
    }

    public void setCubeInHand(String coord) {
        cubeInHand = getCube(coord);
    }

    public void switchPlayers() {
        this.firstPlayerTurn = !firstPlayerTurn;
    }

    public Cube getCube(String coordinates) {
        return this.board.getCubeFromSpot(coordinates);
    }
}