package com.akahraman.server.payment.controller;

import com.akahraman.server.payment.exceptions.InvalidAccountNumber;
import com.akahraman.server.payment.exceptions.InvalidAccountTypeException;
import com.akahraman.server.payment.exceptions.InvalidBalanceLimitException;
import com.akahraman.server.payment.exceptions.InvalidMoneyAmount;
import com.akahraman.server.payment.model.DepositWithdrawModel;
import com.akahraman.server.payment.service.AccountService;
import com.akahraman.server.payment.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WithdrawController class represent withdraw operation api.
 */
@RestController
@RequestMapping("withdraw")
public class WithdrawController {


    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private AccountService accountService;

    public WithdrawController(WithdrawService withdrawService,
                              AccountService accountService) {
        this.withdrawService = withdrawService;
        this.accountService = accountService;
    }

    /**
     * withdrawMoney method help to with draw money from account.
     * @param depositWithdrawModel
     * @return Http status code.
     */
    @PostMapping("")
    public ResponseEntity<?> withdrawMoney(@RequestBody DepositWithdrawModel depositWithdrawModel) {
        try {
            withdrawService.withdrawMoney(depositWithdrawModel);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (InvalidAccountNumber invalidAccountNumber) {
            return ResponseEntity.badRequest().body(invalidAccountNumber.getMessage());
        } catch (InvalidMoneyAmount invalidMoneyAmount) {
            return ResponseEntity.badRequest().body(invalidMoneyAmount.getMessage());
        } catch (InvalidBalanceLimitException invalidBalanceLimitException) {
            return ResponseEntity.badRequest().body(invalidBalanceLimitException.getMessage());
        } catch (InvalidAccountTypeException invalidAccountTypeException) {
            return ResponseEntity.badRequest().body(invalidAccountTypeException.getMessage());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception);
        }
    }
}
