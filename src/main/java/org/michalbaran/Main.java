package org.michalbaran;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Character> characterList = new ArrayList<>();
        characterList.add(Character.DRA);
        characterList.add(Character.HAR);
        characterList.add(Character.DUM);
        characterList.add(Character.HER);

        for (Character c : characterList) {
            System.out.print(c + " ");
        }
    }
}
