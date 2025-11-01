package com.bharatdigital.labour_health.controller;

import com.bharatdigital.labour_health.model.LabourHealthData;
import com.bharatdigital.labour_health.repository.LabourHealthRepository;
import com.bharatdigital.labour_health.service.TokenStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/dispensaries")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDispensaryController {

    private final LabourHealthRepository repo;
    private final TokenStore store;

    public AdminDispensaryController(LabourHealthRepository repo, TokenStore store) {
        this.repo = repo;
        this.store = store;
    }

    private boolean authorized(String authHeader) {
        if (authHeader == null) return false;
        if (!authHeader.startsWith("Bearer ")) return false;
        String token = authHeader.substring(7);
        return store.valid(token);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader(name="Authorization", required=false) String auth) {
        if (!authorized(auth)) return ResponseEntity.status(401).body(Map.of("error","unauthorized"));
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestHeader(name="Authorization", required=false) String auth,
                                    @RequestBody LabourHealthData data) {
        if (!authorized(auth)) return ResponseEntity.status(401).body(Map.of("error","unauthorized"));
        LabourHealthData saved = repo.save(data);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestHeader(name="Authorization", required=false) String auth,
                                    @PathVariable Long id,
                                    @RequestBody LabourHealthData payload) {
        if (!authorized(auth)) return ResponseEntity.status(401).body(Map.of("error","unauthorized"));
        return repo.findById(id).map(existing -> {
            // update fields you need (for brevity copy a few)
            existing.setDispensary_name(payload.getDispensary_name());
            existing.setState(payload.getState());
            existing.setDistrict(payload.getDistrict());
            existing.setOpd_total_static(payload.getOpd_total_static());
            existing.setMedicine_value(payload.getMedicine_value());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader(name="Authorization", required=false) String auth, @PathVariable Long id) {
        if (!authorized(auth)) return ResponseEntity.status(401).body(Map.of("error","unauthorized"));
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }
}
