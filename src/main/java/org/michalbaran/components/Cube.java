package org.michalbaran.components;

import org.michalbaran.enums.Figure;

import java.util.ArrayList;
import java.util.List;

public class Cube {
    List<String> figures = new ArrayList<>();

    public Cube() {
        figures.add("HER");
        System.out.println(Figure.HER);
    }
}
