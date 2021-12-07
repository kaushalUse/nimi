package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.dto.LedgerHolderRequestDTO;
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
import java.util.stream.Collectors;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public Long createLedgerHolderRequest(LedgerHolderRequest ledgerHolderRequest) throws JDBCException {
        ledgerHolderRequest =  ledgerHolderRequestRepository.saveAndFlush(ledgerHolderRequest);
        LOGGER.info("create LedgerHolder Request :"+ ledgerHolderRequest.getId() + " by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());
        return ledgerHolderRequestRepository.saveAndFlush(ledgerHolderRequest).getId();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<LedgerHolderRequestDTO> getLedgerHolderRequests() {
        LOGGER.info("find all LedgerHolder Requests done by " + UtilClass.getCurrentUser());
        List<LedgerHolderRequest> ledgerHolderRequests = ledgerHolderRequestRepository.findAll();
        return ledgerHolderRequests.stream()
                .map(ledgerHolder -> new LedgerHolderRequestDTO(ledgerHolder.getId(), ledgerHolder.getName(), ledgerHolder.getEmail(), ledgerHolder.getComment()))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LedgerHolderRequestDTO getLedgerHolderRequest(Long id) throws EntityNotFoundException {
        LedgerHolderRequest ledgerHolderRequest = ledgerHolderRequestRepository.getById(id);
        LOGGER.info("Get LedgerHolder Request :"+ ledgerHolderRequest.getId() + " by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());
        LedgerHolderRequestDTO ledgerHolderRequestDTO =  new LedgerHolderRequestDTO();
        ledgerHolderRequestDTO.setId(ledgerHolderRequest.getId());
        ledgerHolderRequestDTO.setName(ledgerHolderRequest.getName());
        ledgerHolderRequestDTO.setEmail(ledgerHolderRequest.getEmail());
        ledgerHolderRequestDTO.setComment(ledgerHolderRequest.getComment());
        return ledgerHolderRequestDTO;
    }
}


