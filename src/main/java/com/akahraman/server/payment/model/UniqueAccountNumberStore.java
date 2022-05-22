package com.akahraman.server.payment.model;

import com.akahraman.server.payment.exceptions.NotUniqueAccountNumberException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * UniqueAccountStore class keep unique account number list.
 */
@Component
public class UniqueAccountNumberStore {
    private static UniqueAccountNumberStore INSTANCE;
    private  Set<Integer> accountNumbers;

    private UniqueAccountNumberStore() {
        accountNumbers = new HashSet<>();
    }

    public static UniqueAccountNumberStore getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new UniqueAccountNumberStore();
        }
        return INSTANCE;
    }


    public Set<Integer> getAccountNumbers() {
        return accountNumbers;
    }

    /**
     * add method check given account number exist or not.
     * If account number dont exist add account number to list.
     * @param accountNumber
     * @throws NotUniqueAccountNumberException
     */
    public  void add(Integer accountNumber) throws NotUniqueAccountNumberException {
        if (!accountNumbers.contains(accountNumber)){
           accountNumbers.add(accountNumber);
        }else {
            throw new NotUniqueAccountNumberException("Account number already exist", new IllegalArgumentException());
        }
    }
}
