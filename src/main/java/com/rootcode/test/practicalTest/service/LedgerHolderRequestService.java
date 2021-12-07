package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.entity.LedgerHolderRequest;
import org.hibernate.JDBCException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface LedgerHolderRequestService {

    void deleteLedgerHolder(Long id) throws JDBCException;

    LedgerHolderRequest createLedgerHolderRequest(LedgerHolderRequest ledgerHolderRequest) throws JDBCException;

    List<LedgerHolderRequest> getLedgerHolderRequests();

    LedgerHolderRequest getLedgerHolderRequest(Long id) throws EntityNotFoundException;
}
