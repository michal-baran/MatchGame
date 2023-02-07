package org.michalbaran;

import org.michalbaran.components.Symbol;

import java.util.Set;

public class Player {
    private String name;
    private Set<Symbol> cards;
    private int points;

    public Player(String name) {
        this.name = name;
    }
}
