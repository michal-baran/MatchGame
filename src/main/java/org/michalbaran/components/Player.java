package org.michalbaran.components;

import lombok.Getter;

import java.util.Set;

@Getter
public class Player {
    private final String name;
    private Set<Symbol> cards;
    private int points;

    public Player(String name) {
        this.name = name;
    }
}
