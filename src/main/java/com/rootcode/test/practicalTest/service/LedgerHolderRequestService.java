package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.dto.LedgerHolderRequestDTO;
import com.rootcode.test.practicalTest.entity.LedgerHolderRequest;
import org.hibernate.JDBCException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface LedgerHolderRequestService {

    void deleteLedgerHolder(Long id) throws JDBCException;

    Long createLedgerHolderRequest(LedgerHolderRequest ledgerHolderRequest) throws JDBCException;

    List<LedgerHolderRequestDTO> getLedgerHolderRequests();

    LedgerHolderRequestDTO getLedgerHolderRequest(Long id) throws EntityNotFoundException;
}
