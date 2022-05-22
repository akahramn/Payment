package com.akahraman.server.payment.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * TransactionHistoryStore keep account money transfer information.
 */
@Component
public class TransactionHistoryStore {
    private static TransactionHistoryStore INSTANCE;
    private List<TransactionHistoryModel> transactionData;

    private TransactionHistoryStore(){
        transactionData = new ArrayList<>();
    }

    public static TransactionHistoryStore getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new TransactionHistoryStore();
        }
        return INSTANCE;
    }

    public List<TransactionHistoryModel> getAccounts() {
        return transactionData;
    }

    public boolean addAccount(TransactionHistoryModel transactionHistoryModel) {
        try{
            transactionData.add(transactionHistoryModel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
