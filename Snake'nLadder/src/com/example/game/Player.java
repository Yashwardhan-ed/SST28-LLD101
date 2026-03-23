package com.example.game;

public class Player {
    int playerId;
    String name;
    int position;
    public Player(int playerId, String name) {
        this.playerId = playerId;
        this.name = name;
        this.position = 0; // Starting position
    }
    void makeTurn(int steps, int winningPosition) {
        if (position + steps > winningPosition) {
            System.out.println(this.name + " rolled a " + steps + " but can't move as it exceeds position " + winningPosition + ".");
            return;
        }
        this.position += steps;
        System.out.println("\n" + this.name + " rolled a " + steps + " and moved to position " + this.position + "\n");
    }
    boolean hasWon(int size) {
        return this.position == (size * size);
    }
}
