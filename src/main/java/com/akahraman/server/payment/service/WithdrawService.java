package com.akahraman.server.payment.service;

import com.akahraman.server.payment.enums.AccountType;
import com.akahraman.server.payment.enums.TransactionType;
import com.akahraman.server.payment.exceptions.InvalidAccountNumber;
import com.akahraman.server.payment.exceptions.InvalidAccountTypeException;
import com.akahraman.server.payment.exceptions.InvalidBalanceLimitException;
import com.akahraman.server.payment.exceptions.InvalidMoneyAmount;
import com.akahraman.server.payment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * WithdrawService class include withdraw money operations method.
 */
@Service
public class WithdrawService {
    @Autowired
    AccountService accountService;

    public WithdrawService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * withdraw money method take withdrwa information and withdrow money from form account.
     * @param withdraw
     */
    public void withdrawMoney(DepositWithdrawModel withdraw) {

        if (!UniqueAccountNumberStore.getINSTANCE().getAccountNumbers().contains(withdraw.getAccountNumber())) {
            throw new InvalidAccountNumber("Your account number not exist", new IllegalArgumentException());
        }

        if (withdraw.getAmount() < 0) {
            throw new InvalidMoneyAmount("Please enter valid currency amount", new IllegalArgumentException());
        }

        AccountModel account = accountService.findByAccountNumber(withdraw.getAccountNumber());
        if (account.getAccountType() == AccountType.CORPORATE) {
            throw new InvalidAccountTypeException("Invalid account type please check account type");
        }

        if (account.getBalance()< withdraw.getAmount()) {
            throw new InvalidBalanceLimitException("Your limit is not enough. Please check your balance");
        }

        account.setBalance(account.getBalance() - withdraw.getAmount());

        TransactionHistoryModel transactionHistory = new TransactionHistoryModel();
        transactionHistory.setAccountNumber(account.getAccountNumber());
        transactionHistory.setTransactionType(TransactionType.WITHDRAW);
        transactionHistory.setAmount(withdraw.getAmount());
        transactionHistory.setCreatedAt(new Date());

        TransactionHistoryStore.getINSTANCE().addAccount(transactionHistory);
    }
}
