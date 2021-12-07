package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.entity.LedgerHolderRequest;
import com.rootcode.test.practicalTest.repository.LedgerHolderRequestRepository;
import com.rootcode.test.practicalTest.util.UtilClass;
import org.hibernate.JDBCException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Ledger Holder Request Service related methods
*/

@Service
public class LedgerHolderRequestServiceImpl  implements LedgerHolderRequestService{

    private static final org.slf4j.Logger LOGGER =  LoggerFactory.getLogger(LedgerHolderRequestServiceImpl.class);

    @Autowired
    LedgerHolderRequestRepository ledgerHolderRequestRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLedgerHolder(Long id) throws JDBCException {
        ArrayList<Long> request = new ArrayList<>();
        request.add(id);
        ledgerHolderRequestRepository.deleteAllByIdInBatch(request);
        LOGGER.info("Delete Ledger Holder id : "+ id + " by "+ UtilClass.getCurrentUser() + " at : " + new Date().getTime());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public LedgerHolderRequest createLedgerHolderRequest(LedgerHolderRequest ledgerHolderRequest) throws JDBCException {
        ledgerHolderRequest =  ledgerHolderRequestRepository.saveAndFlush(ledgerHolderRequest);
        LOGGER.info("create LedgerHolder Request :"+ ledgerHolderRequest.getId() + " by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());
        return ledgerHolderRequestRepository.saveAndFlush(ledgerHolderRequest);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<LedgerHolderRequest> getLedgerHolderRequests() {
        LOGGER.info("find all LedgerHolder Requests done by " + UtilClass.getCurrentUser());
        return ledgerHolderRequestRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LedgerHolderRequest getLedgerHolderRequest(Long id) throws EntityNotFoundException {
        LedgerHolderRequest ledgerHolderRequest = ledgerHolderRequestRepository.getById(id);
        LOGGER.info("Get LedgerHolder Request :"+ ledgerHolderRequest.getId() + " by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());
        return ledgerHolderRequest;
    }
}


