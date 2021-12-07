package com.rootcode.test.practicalTest.repository;

import com.rootcode.test.practicalTest.entity.Ledger;
import com.rootcode.test.practicalTest.entity.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;


public interface LedgerRepository extends JpaRepository<Ledger, Long> {


}