package org.example.diabeticcalculatorbackend.repository;

import org.example.diabeticcalculatorbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
