package com.akahraman.server.payment.controller;

import com.akahraman.server.payment.exceptions.InvalidAccountNumber;
import com.akahraman.server.payment.exceptions.InvalidAccountTypeException;
import com.akahraman.server.payment.exceptions.InvalidMoneyAmount;
import com.akahraman.server.payment.model.MoneyTransferModel;
import com.akahraman.server.payment.service.AccountService;
import com.akahraman.server.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PaymentController class represent to payment operations api between INDIVIDUAL account and CORPORATIONS account.
 */
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PaymentService paymentService;

    public PaymentController(AccountService accountService,
                             PaymentService paymentService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
    }

    /**
     * sendMoney method send money INDIVIDUAL account to CORPORATIONS account.
     * @param moneyTransfer information.
     * @return Http status code.
     */
    @PostMapping("")
    public ResponseEntity<?> sendMoney(@RequestBody MoneyTransferModel moneyTransfer) {
        try {
            paymentService.transferMoney(moneyTransfer);
            return ResponseEntity.ok().body("Your transaction was successful.");
        } catch (InvalidAccountNumber invalidAccountNumber) {
            return ResponseEntity.badRequest().body(invalidAccountNumber.getMessage());
        } catch (InvalidMoneyAmount invalidMoneyAmount) {
            return ResponseEntity.badRequest().body(invalidMoneyAmount.getMessage());
        } catch (InvalidAccountTypeException invalidAccountTypeException) {
            return ResponseEntity.badRequest().body(invalidAccountTypeException.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
}
