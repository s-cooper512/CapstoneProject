package org.example.diabeticcalculatorbackend.repository;

import org.example.diabeticcalculatorbackend.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends JpaRepository<Food, Long> {
}
