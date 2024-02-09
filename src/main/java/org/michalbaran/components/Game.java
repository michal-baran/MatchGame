package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.commands.Command;
import org.michalbaran.commands.Show;
import org.michalbaran.enums.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    private Cube currCube;
    private Symbol currSymbol;
    private Command currCommand;
    private final int[] currCoords = new int[2];
    private Player currPlayer;
    private boolean firstPlayerTurn = true;

    public Game() {

        try (Stream<String> cubesStream = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Cubes.txt"))).lines();
             Stream<String> cardsStream = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Cards.txt"))).lines()) {
            cubes = cubesStream
                    .map(Cube::new)
                    .collect(Collectors.toList());
            cards = cardsStream
                    .map(Symbol::valueOf)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("File not found");
        }
        resetBoard();

        currCommand = new
                Show(this);
    }

    public void play() {
        while (currPlayer.getPoints() < 3) {
            currCommand = currCommand.execute();
        }
        System.out.printf("Player %s wins a game with %d points! Congratulations!", currPlayer.getName(), currPlayer.getPoints());
    }

    public void setPlayers() {
        for (int i = 1; i <= 2; i++) {
            System.out.printf("Enter the name of the player %d: ", i);
            Player tempPlayer = new Player(getInput());
            players.add(tempPlayer);
        }
        currPlayer = players.get(0);
        dealCards();
    }

    private void dealCards() {
        Collections.shuffle(cards);
        players.get(0).setCards(cards.get(0), cards.get(1));
        players.get(1).setCards(cards.get(2), cards.get(3));
    }

    public void switchPlayers() {
        firstPlayerTurn = !firstPlayerTurn;
        currPlayer = players.get(firstPlayerTurn ? 0 : 1);
    }

    public void setCurrCube() {
        currCube = getCube();
    }

    public void setCubeInSpot() {
        Cube tempCube = getCube();
        board.setCubeInSpot(currCoords, currCube, currSymbol, firstPlayerTurn);
        currCube = tempCube;
    }

    public Cube getCube() {
        Cube tempCube = board.getCubeFromSpot(currCoords);
        return tempCube;
    }

    public String getInput() {
        String input = sc.next().toUpperCase();
        switch (input) {
            case "/QUIT" -> System.exit(0);
            case "/POINTS" -> {
                for (Player player : players) {
                    System.out.printf("%s points: %d\n", player.getName(), player.getPoints());
                }
            }
            case "/SHOW" -> showBoard();
            case "/HELP" -> System.out.println("""
                    ==== Accessible commands =====
                    /quit, - exits the game
                    /points - shows actual points for each player
                    /show - prints board for actual player
                    """);
            default -> {
                return input;
            }
        }
        return getInput();
    }

    public void showBoard() {
        board.show(currPlayer.getName(), firstPlayerTurn);
    }

    private void resetBoard() {
        System.out.println("\nPreparing new board...");
        Collections.shuffle(cubes);
        board = new Board(cubes);
        Arrays.fill(currCoords, 0);
        setCurrCube();
    }

    public void checkMatch() {
        // check if any player has a match after last turn
        boolean P1hasMatch = board.checkBoardForMatch(currCoords, true, currSymbol);
        boolean P2hasMatch = board.checkBoardForMatch(currCoords, false, currCube.getOppositeSymbol(currSymbol));

        // If one of the players has a match - check if match symbol is present on players cards
        if (P1hasMatch || P2hasMatch) {
            int solution = 1;
            Player otherPlayer = getCurrPlayer().equals(players.get(0)) ? players.get(1) : players.get(0);
            if (currPlayer.getCards().contains(currSymbol)) {
                solution = 2;
            } else if (otherPlayer.getCards().contains(currSymbol)) {
                solution = 3;
            }

            switch (solution) {
                case 1 -> {
                    System.out.printf("Player %s has a match and scores 1 point!\n", currPlayer.getName());
                    currPlayer.addPoints(1);
                }
                case 2 -> {
                    System.out.printf("Player %s has a match and also has matching symbol on his cards so scores 2 points!\n", currPlayer.getName());
                    currPlayer.addPoints(2);
                }
                case 3 -> {
                    System.out.printf("Player %s has a match but player %s has matching symbol on his cards so %s scores 2 points!\n", currPlayer.getName(), otherPlayer.getName(), otherPlayer.getName());
                    otherPlayer.addPoints(2);
                }
            }
            if (currPlayer.getPoints() < 3) {
                resetBoard();
                dealCards();
            }
        }
        if (currPlayer.getPoints() < 3) {
            this.switchPlayers();
        }

    }

    public void setCoordinates() {
        while (true) {
            System.out.println("Type coordinates for your cube:");
            String input = getInput();
            char[] chars = input.toCharArray();

            if (input.matches("[A-E][1-5]")) {
                currCoords[0] = chars[0] - 65;
                currCoords[1] = chars[1] - 49;
                break;
            } else if (input.matches("[1-5][A-E]")) {
                currCoords[1] = chars[1] - 65;
                currCoords[0] = chars[0] - 49;
                break;
            } else {
                System.out.println("Coordinates in wrong format!");
            }
        }
    }

    public void chooseSymbol() {
        while (true) {
            try {
                System.out.printf("Choose a symbol: %s your cards: %s\n", currCube, currPlayer.getCards());
                currSymbol = Symbol.valueOf(getInput());
                if (currCube.isSymbolPresent(currSymbol)) {
                    break;
                }
                System.out.println("Symbol is not accessible on your cube!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public void setEmptySymbol() {
        currSymbol = Symbol.EMP;
    }
}