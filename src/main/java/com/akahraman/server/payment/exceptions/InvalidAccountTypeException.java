package com.akahraman.server.payment.exceptions;

/**
 * InvalidAccountTypeException throw message when if invalid account type try to perform operations.
 */
public class InvalidAccountTypeException extends RuntimeException{
    public InvalidAccountTypeException(String message) {
        super(message);
    }
}
