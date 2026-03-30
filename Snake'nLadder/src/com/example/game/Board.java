package com.example.game;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;

public class Board {
    int size;
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;
    Random random;
    
    public Board(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.random = new Random();
        BuildBoard(size, size);
    }

    void BuildBoard(int snakeCount, int ladderCount) {
        // Add Random Snakes and Ladders
        createRandomSnakes(snakeCount);
        createRandomLadders(ladderCount);
    }

    void createRandomSnakes(int count) {
        int maxCell = size * size;
        int placed = 0;
        int attempts = 0;
        int maxAttempts = Math.max(1000, count * 200);

        while (placed < count && attempts < maxAttempts) {
            attempts++;
            int start = getRandomInRange(2, maxCell - 1);
            int end = getRandomInRange(1, start - 1);

            if (isCellOccupied(start) || isCellOccupied(end)) {
                continue;
            }

            snakes.put(start, end);
            placed++;
        }

        if (placed < count) {
            System.out.println("Could only place " + placed + " snakes out of " + count + ".");
        }
    }

    void createRandomLadders(int count) {
        int maxCell = size * size;
        int placed = 0;
        int attempts = 0;
        int maxAttempts = Math.max(1000, count * 200);

        while (placed < count && attempts < maxAttempts) {
            attempts++;
            int start = getRandomInRange(1, maxCell - 2);
            int end = getRandomInRange(start + 1, maxCell - 1);

            if (isCellOccupied(start) || isCellOccupied(end)) {
                continue;
            }

            ladders.put(start, end);
            placed++;
        }

        if (placed < count) {
            System.out.println("Could only place " + placed + " ladders out of " + count + ".");
        }
    }

    int resolveJump(int position) {
        if(snakes.containsKey(position)) {
            System.out.println("Oh no! You hit a snake! Going down from " + position + " to " + snakes.get(position) + "\n");
            return snakes.get(position);
        } else if(ladders.containsKey(position)) {
            System.out.println("Great! You found a ladder! Climbing up from " + position + " to " + ladders.get(position) + "\n");
            return ladders.get(position);
        }
        return position;
    }

    private int getRandomInRange(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException("Invalid range: min=" + min + ", max=" + max);
        }
        return random.nextInt(max - min + 1) + min;
    }

    private boolean isCellOccupied(int cell) {
        return snakes.containsKey(cell) || snakes.containsValue(cell)
                || ladders.containsKey(cell) || ladders.containsValue(cell);
    }

    void displayBoard() {
        int cellsInRow = 0;
        for (int i = size * size; i >= 1; i--) {
            if(snakes.containsKey(i)) {
                System.out.print("S(" + snakes.get(i) + ")\t");
            } else if(ladders.containsKey(i)) {
                System.out.print("L(" + ladders.get(i) + ")\t");
            } else {
                System.out.print(i + "\t");
            }

            cellsInRow++;
            if (cellsInRow == size) {
                System.out.println();
                cellsInRow = 0;
            }
        }
        System.out.println();
    }

}
