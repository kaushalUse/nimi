package com.rootcode.test.practicalTest.repository;

import com.rootcode.test.practicalTest.entity.LedgerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<LedgerUser, Long> {
    LedgerUser findByUsername(String username);

}