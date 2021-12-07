package com.rootcode.test.practicalTest.repository;

import com.rootcode.test.practicalTest.entity.Role;
import com.rootcode.test.practicalTest.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscationRepository extends JpaRepository<Transaction, Long> {
}