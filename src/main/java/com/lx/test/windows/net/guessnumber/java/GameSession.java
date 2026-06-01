package com.lx.test.windows.net.guessnumber.java;

import java.util.Random;

public class GameSession {
    private static final int MAX_NUMBER = 100;
    private int numberToGuess;
    private boolean player1Ready;
    private boolean player2Ready;
    private boolean gameOver;
    private int winner;

    public GameSession() {
        newGame();
    }

    public void newGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(MAX_NUMBER) + 1;
        player1Ready = false;
        player2Ready = false;
        gameOver = false;
        winner = 0;
    }

    public int checkGuess(int guess, int player) {
        if (guess == numberToGuess) {
            gameOver = true;
            winner = player;
            return 0; // 猜对
        } else if (guess < numberToGuess) {
            return -1; // 猜小了
        } else {
            return 1; // 猜大了
        }
    }

    public boolean isPlayer1Ready() {
        return player1Ready;
    }

    public void setPlayer1Ready(boolean player1Ready) {
        this.player1Ready = player1Ready;
    }

    public boolean isPlayer2Ready() {
        return player2Ready;
    }

    public void setPlayer2Ready(boolean player2Ready) {
        this.player2Ready = player2Ready;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }

    public boolean bothPlayersReady() {
        return player1Ready && player2Ready;
    }
}