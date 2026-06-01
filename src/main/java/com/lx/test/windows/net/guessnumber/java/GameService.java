package com.lx.test.windows.net.guessnumber.java;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {
    private final Map<String, GameSession> gameSessions = new HashMap<>();

    public GameSession getOrCreateSession(String sessionId) {
        return gameSessions.computeIfAbsent(sessionId, k -> new GameSession());
    }
}