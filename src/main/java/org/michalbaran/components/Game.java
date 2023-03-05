package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.commands.*;
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
        dealCards();
        actCommand = new Show(this);
    }

    public void play() {
        do {
            actCommand = actCommand.execute();
        } while (actPlayer.getPoints() < 3);
        System.out.printf("Player %s wins a game with %d points! Congratulations!", actPlayer.getName(), actPlayer.getPoints());
    }

    private void setPlayers() {
        for (int i = 1; i <= 2; i++) {
            System.out.printf("Enter the name of the player %d: ", i);
            Player tempPlayer = new Player(getInput());
            players.add(tempPlayer);
        }
        actPlayer = players.get(0);
    }

    private void dealCards() {
        Collections.shuffle(cards);
        players.get(0).setCards(cards.get(0), cards.get(1));
        players.get(1).setCards(cards.get(2), cards.get(3));
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
        switch (input) {
            case "QUIT": {
                System.exit(0);
                break;
            }
            case "POINTS": {
                for (Player player : players) {
                    System.out.printf("%s points: %d\n", player.getName(), player.getPoints());
                }
                break;
            }
            case "SHOW": {
                showBoard();
                break;
            }
        }
        return input;
    }

    public void showBoard() {
        board.show(actPlayer.getName(), firstPlayerTurn);
    }

    public void resetBoard() {
        System.out.println("Preparing new board...");
        Collections.shuffle(cubes);
        board = new Board(cubes);
        Arrays.fill(actCoords, 0);
        setActCube();
    }

    public void checkMatch() {
        if (board.checkBoardForMatch(actCoords, firstPlayerTurn, actSymbol)) {
            // Actual player has a match - check if match symbol is present on players cards
            Player otherPlayer = getActPlayer().equals(players.get(0)) ? players.get(1) : players.get(0);
            int solution = 1;
            if (actPlayer.getCards().contains(actSymbol)) {
                solution = 2;
            } else if (otherPlayer.getCards().contains(actSymbol)) {
                solution = 3;
            }

            switch (solution) {
                case 1: {
                    System.out.printf("Player %s has a match and scores 1 point!\n", actPlayer.getName());
                    actPlayer.addPoints(1);
                    break;
                }
                case 2: {
                    System.out.printf("Player %s has a match and also has matching symbol on his cards so scores 2 points!\n", actPlayer.getName());
                    actPlayer.addPoints(2);
                    break;
                }
                case 3: {
                    System.out.printf("Player %s has a match but player %s has matching symbol on his cards so %s scores 2 points!\n", actPlayer.getName(), otherPlayer.getName(), otherPlayer.getName());
                    otherPlayer.addPoints(2);
                    break;
                }
            }
            resetBoard();
            dealCards();
        }
        if (actPlayer.getPoints() >= 3) {
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
            try {
                System.out.printf("Choose a symbol: %s your cards: %s\n", actCube, actPlayer.getCards());
                actSymbol = Symbol.valueOf(getInput());
                if (actCube.isSymbolPresent(actSymbol)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                // catch block intentionally blank
            }
        }
    }
}