package com.akahraman.server.payment.service;

import com.akahraman.server.payment.enums.AccountType;
import com.akahraman.server.payment.enums.CurrencyCode;
import com.akahraman.server.payment.exceptions.NotUniqueAccountNumberException;
import com.akahraman.server.payment.model.AccountModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private AccountService accountService = new AccountService();

    @Test
    void createAccountSuccessCreation() throws NotUniqueAccountNumberException {
        //Given
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountNumber(1);
        accountModel.setBalance(100.00);
        accountModel.setAccountType(AccountType.INDIVIDUAL);
        accountModel.setOwnerName("Abdullah");
        accountModel.setCurrencyCode(CurrencyCode.TRY);

        //When
        accountService.createAccount(accountModel);
        //Then
        AccountModel accountWithAccountNumber = accountService.findByAccountNumber(1);
        assertEquals(accountWithAccountNumber.getOwnerName(), accountModel.getOwnerName());
    }
}