package com.akahraman.server.payment.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * AccountStore keeps created accounts.
 */
@Component
public class AccountStore {
    private static AccountStore INSTANCE;
    private List<AccountModel> accountData;

    private AccountStore(){
        accountData = new ArrayList<>();
    }

    public static AccountStore getINSTANCE(){
        if(INSTANCE == null){
           INSTANCE = new AccountStore();
        }
        return INSTANCE;
    }

    public List<AccountModel> getAccounts() {
        return accountData;
    }

    public boolean addAccount(AccountModel accountModel) {
        try{
            accountData.add(accountModel);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
