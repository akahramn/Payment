package com.akahraman.server.payment.controller;


import com.akahraman.server.payment.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TransactionHistoryController represent transaction history opreations api.
 */
@RestController
@RequestMapping("accounting")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    /**
     * getTransactionHistory method help to get account transaction history.
     * @param accountNumber
     * @return Http status code.
     */
    @GetMapping("")
    public ResponseEntity<?> getTransactionHistory(@RequestParam Integer accountNumber) {

        return ResponseEntity.ok().body(transactionHistoryService.getAccountTransactionHistory(accountNumber));

    }
}
