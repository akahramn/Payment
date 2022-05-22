package com.akahraman.server.payment.service;

import com.akahraman.server.payment.exceptions.NotUniqueAccountNumberException;
import com.akahraman.server.payment.model.AccountStore;
import com.akahraman.server.payment.model.AccountModel;
import com.akahraman.server.payment.model.UniqueAccountNumberStore;
import org.springframework.stereotype.Service;

/**
 * AccountService class include accounting operations
 */
@Service
public class AccountService {

    /**
     * createAccount method take Account model and create new account.
     * @param accountModel
     * @throws NotUniqueAccountNumberException
     */
    public void createAccount(AccountModel accountModel) throws NotUniqueAccountNumberException {
        UniqueAccountNumberStore.getINSTANCE().add(accountModel.getAccountNumber());
        AccountStore.getINSTANCE().addAccount(accountModel);
    }

    /**
     * findAccountWithAccountNumber method take account number from client and return account informations.
     * @param accountNumber
     * @return AccountModel instance
     */
    public AccountModel findByAccountNumber(Integer accountNumber) {
        AccountModel accountModel = AccountStore.getINSTANCE().getAccounts().stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findAny()
                .orElse(null);
        return accountModel;
    }
}
