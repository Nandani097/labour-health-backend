package com.bharatdigital.labour_health.controller;

import com.bharatdigital.labour_health.model.LabourHealthData;
import com.bharatdigital.labour_health.repository.LabourHealthRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LabourHealthController {

    private final LabourHealthRepository repository;

    public LabourHealthController(LabourHealthRepository repository) {
        this.repository = repository;
    }

    // ✅ Fetch all data
    @GetMapping("/dispensaries")
    public List<LabourHealthData> getAllDispensaries() {
        return repository.findAll();
    }

    // ✅ Fetch one record by ID
    @GetMapping("/dispensaries/{id}")
    public Optional<LabourHealthData> getDispensaryById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // ✅ Add new record
    @PostMapping("/dispensaries")
    public LabourHealthData addDispensary(@RequestBody LabourHealthData data) {
        return repository.save(data);
    }

    // ✅ Update existing record
    @PutMapping("/dispensaries/{id}")
    public LabourHealthData updateDispensary(@PathVariable Long id, @RequestBody LabourHealthData newData) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setDispensary_name(newData.getDispensary_name());
                    existing.setState(newData.getState());
                    existing.setDistrict(newData.getDistrict());
                    existing.setOpd_total_static(newData.getOpd_total_static());
                    existing.setMedicine_value(newData.getMedicine_value());
                    return repository.save(existing);
                })
                .orElseGet(() -> {
                    newData.setId(id);
                    return repository.save(newData);
                });
    }

    // ✅ Delete a record
    @DeleteMapping("/dispensaries/{id}")
    public void deleteDispensary(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // ✅ Default check route
    @GetMapping("/")
    public String home() {
        return "✅ Labour Health Application is Running Successfully!";
    }
    
}
