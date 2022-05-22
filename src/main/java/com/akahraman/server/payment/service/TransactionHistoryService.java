package com.akahraman.server.payment.service;

import com.akahraman.server.payment.model.TransactionHistoryStore;
import com.akahraman.server.payment.model.TransactionHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TransactionHistoryService include transaction operation methods.
 */
@Service
public class TransactionHistoryService {


    /**
     * getAccountTransactionHistory method take accountNUmber and return account transaction history.
     * @param accountNumber
     * @return transaction history list
     */
    public List<TransactionHistoryModel> getAccountTransactionHistory(Integer accountNumber) {
        return TransactionHistoryStore.getINSTANCE().getAccounts().stream()
                .filter(transactionHistoryModel -> accountNumber.equals(transactionHistoryModel.getAccountNumber()))
                .collect(Collectors.toList());
    }
}
