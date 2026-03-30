package com.example.game;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board size:");
        int boardSize = sc.nextInt();
        System.out.println("Enter number of players:");
        int numPlayers = sc.nextInt();
        Board board = new Board(boardSize);
        Player[] players = new Player[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for Player " + (i+1) + ":");
            String name = sc.next();
            players[i] = new Player(i+1, name);
        }
        
        System.out.println("Choose Game Mode: 1. Easy Mode 2. Hard Mode");
        int gameMode = sc.nextInt();
        GameMode mode = GameFactory.createGameMode(gameMode == 1 ? Mode.EASY : Mode.HARD);
        int winningPosition = board.size * board.size;
        sc.nextLine();

        boolean gameWon = false;
        
        while(!gameWon) {
            for(Player player : players) {
                board.displayBoard();

                System.out.println(player.name + ", press Enter to roll the dice...");
                sc.nextLine();

                mode.makeMove(player, winningPosition);
                player.position = board.resolveJump(player.position);
                if(player.hasWon(board.size)) {
                    System.out.println(player.name + " wins the game!");
                    gameWon = true;
                    break;
                }
            }
        }
        sc.close();
    }
}
