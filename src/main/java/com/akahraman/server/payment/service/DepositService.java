package com.akahraman.server.payment.service;

import com.akahraman.server.payment.enums.AccountType;
import com.akahraman.server.payment.enums.TransactionType;
import com.akahraman.server.payment.exceptions.InvalidAccountNumber;
import com.akahraman.server.payment.exceptions.InvalidAccountTypeException;
import com.akahraman.server.payment.exceptions.InvalidMoneyAmount;
import com.akahraman.server.payment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * DepositService class include deposit operation method.
 */
@Service
public class DepositService {

    @Autowired
    private AccountService accountService;

    public DepositService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * depositMoney method take deposit information and add amount to account
     * balance and add this operations to transaction history.
     * @param deposit
     */
    public void depositMoney(DepositWithdrawModel deposit) {
        if (!UniqueAccountNumberStore.getINSTANCE().getAccountNumbers().contains(deposit.getAccountNumber())) {
            throw new InvalidAccountNumber("Your account number not exist", new IllegalArgumentException());
        }

        if (deposit.getAmount() < 0) {
            throw new InvalidMoneyAmount("Please enter valid currency amount", new IllegalArgumentException());
        }

        AccountModel account = accountService.findByAccountNumber(deposit.getAccountNumber());

        if (account.getAccountType() == AccountType.CORPORATE) {
            throw new InvalidAccountTypeException("Invalid account type please check account type");
        }

            account.setBalance(account.getBalance() + deposit.getAmount());

            TransactionHistoryModel transactionHistory = new TransactionHistoryModel();
            transactionHistory.setAccountNumber(account.getAccountNumber());
            transactionHistory.setTransactionType(TransactionType.DEPOSIT);
            transactionHistory.setAmount(deposit.getAmount());
            transactionHistory.setCreatedAt(new Date());

            TransactionHistoryStore.getINSTANCE().addAccount(transactionHistory);
    }
}
