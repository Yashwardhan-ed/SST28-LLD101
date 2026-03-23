package com.example.game;

public class EasyMode implements GameMode {
    @Override
    public void makeMove(Player player, int winningPosition) {
        int steps = Dice.rollDice();
        while (steps == 6) {
            System.out.println(player.name + " rolled a 6! Gets an extra turn.");
            player.makeTurn(steps, winningPosition);
            steps = Dice.rollDice();
        }
        player.makeTurn(steps, winningPosition);
    }
}
