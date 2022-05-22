package com.akahraman.server.payment.model;

import com.akahraman.server.payment.enums.AccountType;
import com.akahraman.server.payment.enums.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

/**
 * AccountModel represent to account information.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountModel {

    private Integer accountNumber;
    private CurrencyCode currencyCode;
    private String ownerName;
    private AccountType accountType;
    private double balance;

    public void setBalance(double balance) {
        DecimalFormat df = new DecimalFormat("#.00");
        this.balance =new Double(df.format(balance));
    }
}
