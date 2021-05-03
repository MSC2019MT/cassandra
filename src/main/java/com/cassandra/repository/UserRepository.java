package com.cassandra.repository;

import com.cassandra.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> getUserById(Long id);
}
