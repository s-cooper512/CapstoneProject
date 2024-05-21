package org.example.diabeticcalculatorbackend.repository;

import org.example.diabeticcalculatorbackend.model.UserDosageProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDosageProfileRepository extends JpaRepository<UserDosageProfile, Long> {
}
