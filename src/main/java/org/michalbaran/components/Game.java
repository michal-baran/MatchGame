package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.states.State;
import org.michalbaran.states.ShowState;
import org.michalbaran.enums.Symbol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Game {
    private Scanner sc;
    private Board board;
    private List<Cube> cubes;
    private final List<Player> players = new ArrayList<>();
    private List<Symbol> cards;
    private Cube currentCube;
    private Symbol currentSymbol;
    private State currentState;
    private final int[] currentCoordinates = new int[2];
    private Player currentPlayer;
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
        currentState = new ShowState();
    }

    public void play() {
        try (Scanner sc = new Scanner(System.in)) {
            this.sc = sc;
            while (currentPlayer.getPoints() < 3) {
                currentState.execute(this);
            }
            System.out.printf("Player %s wins a game with %d points! Congratulations!", currentPlayer.getName(), currentPlayer.getPoints());
        }
    }

    public void setPlayers() {
        for (int i = 1; i <= 2; i++) {
            System.out.printf("Enter the name of the player %d: ", i);
            Player tempPlayer = new Player(getInput());
            players.add(tempPlayer);
        }
        currentPlayer = players.get(0);
        dealCards();
    }

    private void dealCards() {
        Collections.shuffle(cards);
        players.get(0).setCards(cards.get(0), cards.get(1));
        players.get(1).setCards(cards.get(2), cards.get(3));
    }

    public void switchPlayers() {
        firstPlayerTurn = !firstPlayerTurn;
        currentPlayer = players.get(firstPlayerTurn ? 0 : 1);
    }

    public void setCurrentCube() {
        currentCube = getCube();
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public void setCubeInSpot() {
        Cube tempCube = getCube();
        board.setCubeInSpot(currentCoordinates, currentCube, currentSymbol, firstPlayerTurn);
        currentCube = tempCube;
    }

    public Cube getCube() {
        return board.getCubeFromSpot(currentCoordinates);
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
        board.show(currentPlayer.getName(), firstPlayerTurn);
    }

    private void resetBoard() {
        System.out.println("\nPreparing new board...");
        Collections.shuffle(cubes);
        board = new Board(cubes);
        Arrays.fill(currentCoordinates, 0);
        setCurrentCube();
    }

    public void checkMatch() {
        // check if any player has a match after last turn
        boolean P1hasMatch = board.checkBoardForMatch(currentCoordinates, true, currentSymbol);
        boolean P2hasMatch = board.checkBoardForMatch(currentCoordinates, false, currentCube.getOppositeSymbol(currentSymbol));

        // If one of the players has a match - check if match symbol is present on players cards
        if (P1hasMatch || P2hasMatch) {
            int solution = 1;
            Player otherPlayer = getCurrentPlayer().equals(players.get(0)) ? players.get(1) : players.get(0);
            if (currentPlayer.getCards().contains(currentSymbol)) {
                solution = 2;
            } else if (otherPlayer.getCards().contains(currentSymbol)) {
                solution = 3;
            }

            switch (solution) {
                case 1 -> {
                    System.out.printf("Player %s has a match and scores 1 point!\n", currentPlayer.getName());
                    currentPlayer.addPoints(1);
                }
                case 2 -> {
                    System.out.printf("Player %s has a match and also has matching symbol on his cards so scores 2 points!\n", currentPlayer.getName());
                    currentPlayer.addPoints(2);
                }
                case 3 -> {
                    System.out.printf("Player %s has a match but player %s has matching symbol on his cards so %s scores 2 points!\n", currentPlayer.getName(), otherPlayer.getName(), otherPlayer.getName());
                    otherPlayer.addPoints(2);
                }
            }
            if (currentPlayer.getPoints() < 3) {
                resetBoard();
                dealCards();
            }
        }
        if (currentPlayer.getPoints() < 3) {
            this.switchPlayers();
        }

    }

    public void setCoordinates() {
        while (true) {
            System.out.println("Type coordinates for your cube:");
            String input = getInput();
            char[] chars = input.toCharArray();

            if (input.matches("[A-E][1-5]")) {
                currentCoordinates[0] = chars[0] - 65;
                currentCoordinates[1] = chars[1] - 49;
                break;
            } else if (input.matches("[1-5][A-E]")) {
                currentCoordinates[1] = chars[1] - 65;
                currentCoordinates[0] = chars[0] - 49;
                break;
            } else {
                System.out.println("Coordinates in wrong format!");
            }
        }
    }

    public void chooseSymbol() {
        while (true) {
            try {
                System.out.printf("Choose a symbol: %s your cards: %s\n", currentCube, currentPlayer.getCards());
                currentSymbol = Symbol.valueOf(getInput());
                if (currentCube.isSymbolPresent(currentSymbol)) {
                    break;
                }
                System.out.println("Symbol is not accessible on your cube!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public void setEmptySymbol() {
        currentSymbol = Symbol.EMP;
    }
}