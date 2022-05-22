package com.akahraman.server.payment.model;

import lombok.Data;

/**
 * MoneyTransferModel represent to Money transfer information between INDIVIDUAL account to CORPORATION account.
 */
@Data
public class MoneyTransferModel {

    private Integer senderAccount;
    private Integer receiverAccount;
    private Double amount;
}
