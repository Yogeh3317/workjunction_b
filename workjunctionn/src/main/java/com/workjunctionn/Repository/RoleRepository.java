package com.workjunctionn.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.workjunctionn.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
   // Role findByName(String name);

	Optional<Role> findFirstByName(String name);
}
