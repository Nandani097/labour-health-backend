package com.bharatdigital.labour_health.controller;

import com.bharatdigital.labour_health.model.AdminUser;
import com.bharatdigital.labour_health.repository.AdminUserRepository;
import com.bharatdigital.labour_health.service.TokenStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AdminUserRepository repo;
    private final TokenStore store;

    public AuthController(AdminUserRepository repo, TokenStore store) {
        this.repo = repo;
        this.store = store;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) return ResponseEntity.badRequest().body(Map.of("error","username/password missing"));
        if (repo.findByUsername(username).isPresent()) return ResponseEntity.badRequest().body(Map.of("error","username exists"));
        AdminUser u = new AdminUser();
        u.setUsername(username);
        u.setPassword(password); // prototype only — hash in prod
        repo.save(u);
        return ResponseEntity.ok(Map.of("ok", true));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null) return ResponseEntity.badRequest().body(Map.of("error","missing"));
        var opt = repo.findByUsername(username);
        if (opt.isEmpty() || !opt.get().getPassword().equals(password)) {
            return ResponseEntity.status(401).body(Map.of("error","invalid credentials"));
        }
        String token = store.createToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name="Authorization", required=false) String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            store.revoke(auth.substring(7));
        }
        return ResponseEntity.ok(Map.of("ok", true));
    }
}
