package com.example.game;

public class GameFactory {
    public static GameMode createGameMode(Mode mode) {
        return switch (mode) {
            case EASY -> new EasyMode();
            case HARD -> new HardMode();
            default -> new EasyMode();
        };
    }
}
