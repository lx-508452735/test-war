package com.lx.test.windows.net.guessnumber.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/guess")
    public int guess(@RequestParam int guess, @RequestParam int player, HttpSession session) {
        GameSession gameSession = gameService.getOrCreateSession(session.getId());
        return gameSession.checkGuess(guess, player);
    }

    @PostMapping("/ready")
    public boolean ready(@RequestParam int player, HttpSession session) {
        GameSession gameSession = gameService.getOrCreateSession(session.getId());
        if (player == 1) {
            gameSession.setPlayer1Ready(true);
        } else {
            gameSession.setPlayer2Ready(true);
        }
        if (gameSession.bothPlayersReady()) {
            gameSession.newGame();
            return true;
        }
        return false;
    }

    @GetMapping("/status")
    public GameStatus getStatus(HttpSession session) {
        GameSession gameSession = gameService.getOrCreateSession(session.getId());
        return new GameStatus(gameSession.isGameOver(), gameSession.getWinner());
    }

    static class GameStatus {
        private final boolean gameOver;
        private final int winner;

        public GameStatus(boolean gameOver, int winner) {
            this.gameOver = gameOver;
            this.winner = winner;
        }

        public boolean isGameOver() {
            return gameOver;
        }

        public int getWinner() {
            return winner;
        }
    }
}