package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.dto.LedgerHolderRequestDTO;
import com.rootcode.test.practicalTest.dto.TransactionDTO;
import com.rootcode.test.practicalTest.dto.TransactionRequest;
import com.rootcode.test.practicalTest.entity.Ledger;
import com.rootcode.test.practicalTest.entity.Transaction;
import com.rootcode.test.practicalTest.exception.TansactionException;
import com.rootcode.test.practicalTest.repository.LedgerRepository;
import com.rootcode.test.practicalTest.repository.TranscationRepository;
import com.rootcode.test.practicalTest.util.UtilClass;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
Ledge rHolder Request Service related methods
*/

@Service
public class TransactionServiceImpl implements TransactionService{

    private static final org.slf4j.Logger LOGGER =  LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TranscationRepository transcationRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<TransactionDTO> getAll() {
        LOGGER.info("find Transactions done by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());
            List<Transaction> transactions = transcationRepository.findAll();
        return transactions.stream()
                .map(transaction -> new TransactionDTO(transaction.getId(), TransactionDTO.Type.valueOf(transaction.getType().name()),
                        transaction.getAmount(), transaction.getCurrency(),
                        transaction.getFromLedger(),transaction.getToLedger(), transaction.getCreatedDate()))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTransaction(TransactionRequest transactionRequest) throws TransactionException, TansactionException {

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(transactionRequest.getAmount()));

        Ledger fromLedger = ledgerRepository.getById(transactionRequest.getFromLedger());
        Ledger toLedger = ledgerRepository.getById(transactionRequest.getToLedger());

        transaction.setFromLedger(fromLedger);
        transaction.setToLedger(toLedger);
        ;
        transaction.setCreatedDate(new Date());
        transaction.setCurrency("LKR");
        transaction.setType(Transaction.Type.valueOf(transactionRequest.getType()));
        try {
            transaction  = transcationRepository.saveAndFlush(transaction);
        } catch (Exception e) {
            throw new TansactionException("Transaction Save Exception occurred");
        }
        Long fromLedgerBalance = fromLedger.getBalance();
        Long toLedgerBalance = toLedger.getBalance();

        if (fromLedgerBalance < transactionRequest.getAmount()) {
            throw new TansactionException("Not enough funds in account");
        }
        fromLedger.setBalance(fromLedgerBalance - transactionRequest.getAmount());
        try {
            ledgerRepository.saveAndFlush(fromLedger);
        } catch (Exception e) {
            throw new TansactionException("Transaction DEBIT occurred");
        }
        toLedger.setBalance(toLedgerBalance + transactionRequest.getAmount());
        try {
            ledgerRepository.saveAndFlush(toLedger);
        } catch (Exception e) {
            throw new TansactionException("Transaction DEBIT occurred");
        }

        LOGGER.info("Transaction id "+transaction.getId() + " done by " + UtilClass.getCurrentUser() + " at : " + new Date().getTime());

    }
}
