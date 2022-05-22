package com.akahraman.server.payment.model;


import com.akahraman.server.payment.enums.TransactionType;
import lombok.Data;

import java.util.Date;

/**
 * TransactionHistoryModel represent money transfer model.
 */
@Data
public class TransactionHistoryModel {

    private Integer accountNumber;
    private double amount;
    private TransactionType transactionType;
    private Date createdAt;
}
