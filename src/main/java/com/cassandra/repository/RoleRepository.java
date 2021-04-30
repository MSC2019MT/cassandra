package com.cassandra.repository;

import com.cassandra.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    public Optional<Role> getRoleById(Long id);
}
