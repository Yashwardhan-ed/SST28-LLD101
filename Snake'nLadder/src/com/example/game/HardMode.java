package com.example.game;

import java.util.ArrayList;
import java.util.List;

public class HardMode implements GameMode {
    @Override
    public void makeMove(Player player, int winningPosition) {
        int consecutiveSixes = 0;
        List<Integer> rolls = new ArrayList<>();

        while (true) {
            int steps = Dice.rollDice();
            rolls.add(steps);

            if (steps == 6) {
                consecutiveSixes++;
                if (consecutiveSixes == 3) {
                    System.out.println(player.name + " rolled 3 consecutive 6s! Turn skipped.");
                    return;
                }
                System.out.println(player.name + " rolled a 6! Gets an extra turn.");
                continue;
            }
            break;
        }

        for (int steps : rolls) {
            player.makeTurn(steps, winningPosition);
        }
    }
}