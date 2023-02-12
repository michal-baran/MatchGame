package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;
import org.michalbaran.enums.Symbol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Game {
    private final Scanner sc = new Scanner(System.in);
    private Board board;
    private List<Cube> cubes;
    private final List<Player> players = new ArrayList<>();
    private List<Symbol> cards;
    private Cube actCube;
    private Symbol actSymbol;
    private Command actCommand;
    private final int[] actCoords = new int[2];
    private Player actPlayer;
    private boolean firstPlayerTurn = true;

    public Game() {
        try (Stream<String> cubesStream = Files.lines(Path.of("src/main/resources/Cubes.txt"));
             Stream<String> cardsStream = Files.lines(Path.of("src/main/resources/Cards.txt"))) {
            cubes = cubesStream.map(Cube::new).collect(Collectors.toList());
            cards = cardsStream.map(Symbol::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("File not found");
        }
        resetBoard();
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
        players.add(new Player(getInput(), cards.get(0), cards.get(1)));
        System.out.println("Enter the name of the second player : ");
        players.add(new Player(getInput(), cards.get(2), cards.get(3)));
        actPlayer = players.get(0);
    }

    public void switchPlayers() {
        firstPlayerTurn = !firstPlayerTurn;
        actPlayer = players.get(firstPlayerTurn ? 0 : 1);
    }

    public void setActCube() {
        actCube = getCube();
    }

    public void setCubeInSpot() {
        Cube tempCube = getCube();
        board.setCubeInSpot(actCoords, actCube, actSymbol, firstPlayerTurn);
        actCube = tempCube;
    }

    public Cube getCube() {
        return board.getCubeFromSpot(actCoords);
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

    public void resetBoard() {
        Collections.shuffle(cubes);
        Collections.shuffle(cards);
        board = new Board(cubes);
        Arrays.fill(actCoords, 0);
        setActCube();
    }

    public void checkMatch() {
        if (board.checkBoardForMatch(actCoords, firstPlayerTurn, actSymbol)) {
            System.out.printf("player %s has a match!\n", actPlayer.getName());
            actPlayer.addPoint();
            resetBoard();
        }
        if (actPlayer.getPoints() >= 5) {
            System.out.println("Player " + actPlayer.getName() + " wins a game!");
        }
    }

    public void setCoordinates() {
        while (true) {
            System.out.println("Type coordinates for your cube:");
            String input = getInput();
            char[] chars = input.toCharArray();

            if (input.matches("[A-E][1-5]")) {
                actCoords[0] = chars[0] - 65;
                actCoords[1] = chars[1] - 49;
                break;
            } else if (input.matches("[1-5][A-E]")) {
                actCoords[1] = chars[1] - 65;
                actCoords[0] = chars[0] - 49;
                break;
            } else {
                System.out.println("Coordinates in wrong format!");
            }
        }
    }

    public void chooseSymbol() {
        while (true) {
            System.out.printf("Choose a symbol: %s\n", actCube);
            actSymbol = Symbol.valueOf(getInput());
            if (actCube.isSymbolPresent(actSymbol)) {
                break;
            }
            System.out.println("There is no such symbol on your cube!");
        }
    }
}