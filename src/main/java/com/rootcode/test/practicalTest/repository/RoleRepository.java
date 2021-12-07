package com.rootcode.test.practicalTest.repository;

import com.rootcode.test.practicalTest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}