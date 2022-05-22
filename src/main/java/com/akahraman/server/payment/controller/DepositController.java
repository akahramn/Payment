package com.akahraman.server.payment.controller;

import com.akahraman.server.payment.exceptions.InvalidAccountNumber;
import com.akahraman.server.payment.exceptions.InvalidAccountTypeException;
import com.akahraman.server.payment.exceptions.InvalidMoneyAmount;
import com.akahraman.server.payment.model.DepositWithdrawModel;
import com.akahraman.server.payment.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DepositController class represent deposit operations api.
 */

@RestController
@RequestMapping("deposit")
public class DepositController {


    @Autowired
    private DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    /**
     * depositMoney method help to deposit money to account
     * @param deposit
     * @return Http status code.
     */
    @PostMapping("")
    public ResponseEntity<?> depositMoney(@RequestBody DepositWithdrawModel deposit) {
        try {
            depositService.depositMoney(deposit);
            return ResponseEntity.ok().body(HttpStatus.OK);
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