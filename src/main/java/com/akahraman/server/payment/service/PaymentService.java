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
 * PaymentService class include payment operations method.
 */
@Service
public class PaymentService {
    @Autowired
    AccountService accountService;

    public PaymentService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * transferMoney method take money transfer information and perform
     * money transfer individual account to corporate account.
     *
     * @param moneyTransfer
     */
    public void transferMoney(MoneyTransferModel moneyTransfer) {
        if (!UniqueAccountNumberStore.getINSTANCE().getAccountNumbers().contains(moneyTransfer.getSenderAccount()) |
                !UniqueAccountNumberStore.getINSTANCE().getAccountNumbers().contains(moneyTransfer.getReceiverAccount())) {
            throw new InvalidAccountNumber("Your account number not exist", new IllegalArgumentException());
        }

        if (moneyTransfer.getAmount() < 0) {
            throw new InvalidMoneyAmount("Please enter valid currency amount", new IllegalArgumentException());
        }

        AccountModel senderAccount = accountService.findByAccountNumber(moneyTransfer.getSenderAccount());
        AccountModel receiverAccount = accountService.findByAccountNumber(moneyTransfer.getReceiverAccount());

        if (!(senderAccount.getAccountType() == AccountType.INDIVIDUAL) | !(receiverAccount.getAccountType() == AccountType.CORPORATE)) {
            throw new InvalidAccountTypeException("Invalid account type please check account type");
        }

        senderAccount.setBalance(senderAccount.getBalance() - moneyTransfer.getAmount());
        receiverAccount.setBalance(receiverAccount.getBalance() + moneyTransfer.getAmount());

        TransactionHistoryModel transactionHistory = new TransactionHistoryModel();
        transactionHistory.setAccountNumber(senderAccount.getAccountNumber());
        transactionHistory.setTransactionType(TransactionType.PAYMENT);
        transactionHistory.setAmount(moneyTransfer.getAmount());
        transactionHistory.setCreatedAt(new Date());

        TransactionHistoryStore.getINSTANCE().addAccount(transactionHistory);
    }
}
