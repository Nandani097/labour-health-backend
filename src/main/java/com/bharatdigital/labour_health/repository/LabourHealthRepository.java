package com.bharatdigital.labour_health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bharatdigital.labour_health.model.LabourHealthData;

public interface LabourHealthRepository extends JpaRepository<LabourHealthData, Long> {
}

