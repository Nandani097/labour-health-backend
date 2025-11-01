package com.bharatdigital.labour_health.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    // token -> username
    private final Map<String, String> tokens = new ConcurrentHashMap<>();

    public String createToken(String username) {
        String t = UUID.randomUUID().toString();
        tokens.put(t, username);
        return t;
    }

    public boolean valid(String token) {
        return token != null && tokens.containsKey(token);
    }

    public String usernameOf(String token) {
        return tokens.get(token);
    }

    public void revoke(String token) {
        tokens.remove(token);
    }
}
