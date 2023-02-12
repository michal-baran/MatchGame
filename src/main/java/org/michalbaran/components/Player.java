package org.michalbaran.components;

import lombok.Getter;
import org.michalbaran.enums.Symbol;

import java.util.Set;

@Getter
public class Player {
    private final String name;
    private final Set<Symbol> cards;
    private int points;

    public Player(String name, Symbol card1, Symbol card2) {
        this.name = name;
        cards = Set.of(card1, card2);
    }

    public void addPoint() {
        points++;
    }
}
