package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    private final List<Player> players = new ArrayList<>();
    private Player actPlayer;
    private boolean firstPlayerTurn = true;
    private final Scanner sc = new Scanner(System.in);

    public Game(String input) {
        try (Stream<String> inputStream = Files.lines(Path.of(input))) {
            cubes = inputStream
                    .map(Cube::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        board = new Board(cubes);
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
        System.out.println("Enter the name of the first player : ");
        players.add(new Player(getInput()));
        System.out.println("Enter the name of the second player : ");
        players.add(new Player(getInput()));
        actPlayer = players.get(0);
    }

    public void setCubeInHand(String coord) {
        cubeInHand = getCube(coord);
    }

    public void switchPlayers() {
        firstPlayerTurn = !firstPlayerTurn;
        actPlayer = players.get(firstPlayerTurn ? 0 : 1);
    }

    public Cube getCube(String coordinates) {
        return board.getCubeFromSpot(coordinates);
    }

    public String getInput() {
        String input = sc.next().toUpperCase();
        if (input.equals("EXIT")) {
            System.exit(0);
        }
        return input;
    }

    public void showBoard() {
        board.show(firstPlayerTurn);
    }

    public void setCubeInSpot(String coordinate, Symbol symbol) {
        Cube tempCube = board.getCubeFromSpot(coordinate);
        board.setCubeInSpot(coordinate, cubeInHand, symbol, firstPlayerTurn);
        cubeInHand = tempCube;
    }
}