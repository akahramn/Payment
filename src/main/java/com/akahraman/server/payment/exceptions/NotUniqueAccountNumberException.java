package com.akahraman.server.payment.exceptions;

/**
 * NotUniqueAccountNumberException is throwing message when you try to create an account that already exists.
 */
public class NotUniqueAccountNumberException extends Exception {
    public NotUniqueAccountNumberException(String errorMessage, Throwable err) {
        super(errorMessage);
    }
}
