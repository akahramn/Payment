package com.akahraman.server.payment.controller;

import com.akahraman.server.payment.exceptions.NotUniqueAccountNumberException;
import com.akahraman.server.payment.model.AccountStore;
import com.akahraman.server.payment.model.AccountModel;
import com.akahraman.server.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The AccountController Class represent account api operations.
 */
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountStore accountStore;

    public AccountController(AccountService accountService,
                             AccountStore accountStore) {
        this.accountService = accountService;
        this.accountStore = accountStore;
    }

    /**
     * createAccount method help to create new account.
     * @param accountModel
     * @return HttpStatus Code or exception message.
     * @throws NotUniqueAccountNumberException
     */
    @PostMapping(path = "")
    public ResponseEntity<?> createAccount(@RequestBody AccountModel accountModel) throws NotUniqueAccountNumberException {
        try {
            accountService.createAccount(accountModel);
            return ResponseEntity.ok().body(HttpStatus.CREATED);
        } catch (NotUniqueAccountNumberException notUniqueAccountNumberException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notUniqueAccountNumberException.getMessage());
        }

    }

    /**
     * getAccountInfo methods help to get account information.
     * @param accountNumber
     * @return Http status code.
     */
    @GetMapping("")
    public ResponseEntity<?> getAccountInfo(@RequestParam Integer accountNumber) {
        AccountModel account = accountService.findByAccountNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok().body(account);
        }
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }
}
