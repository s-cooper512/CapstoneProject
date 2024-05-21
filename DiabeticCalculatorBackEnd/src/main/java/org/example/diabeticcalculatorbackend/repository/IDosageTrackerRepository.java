package org.example.diabeticcalculatorbackend.repository;

import org.example.diabeticcalculatorbackend.model.DosageTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDosageTrackerRepository extends JpaRepository<DosageTracker, Long> {
}
