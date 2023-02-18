package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.enums.Symbol;

import java.util.Set;

@Getter
public class Player {
    private final String name;
    private Set<Symbol> cards;
    private int points;

    public Player(String name) {
        this.name = name;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setCards(Symbol card1, Symbol card2) {
        cards = Set.of(card1, card2);
    }
}
