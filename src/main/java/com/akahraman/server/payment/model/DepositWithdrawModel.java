package com.akahraman.server.payment.model;

/**
 * DepositWithdrawModel represent to deposit and withdraw operations information.
 */
public class DepositWithdrawModel {

    private Integer accountNumber;
    private double amount;

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
