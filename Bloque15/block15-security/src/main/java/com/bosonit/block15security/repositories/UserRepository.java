package com.bosonit.block15security.repositories;

import com.bosonit.block15security.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    Optional<User> findOneByEmail(String email);
}

