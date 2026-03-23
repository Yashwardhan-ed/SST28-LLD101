package com.example.game;

import java.util.Random;

public class Dice {
    private static Dice instance;
    private static Random random;
    private Dice() {
        random = new Random();
    }

    public static Dice getInstance() {
        if (instance == null) {
            synchronized (Dice.class) {
                if (instance == null) {
                    instance = new Dice();
                }
            }
        }
        return instance;
    }

    public static int rollDice() {
        if(instance == null) {
            getInstance();
        }
        return (int) (random.nextInt(6) + 1);
    }
}