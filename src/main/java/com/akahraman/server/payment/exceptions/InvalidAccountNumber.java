package com.akahraman.server.payment.exceptions;

/**
 * InvalidAccountNumber exception throw message if account number not exist.
 */
public class InvalidAccountNumber extends RuntimeException{
    public InvalidAccountNumber(String message, Throwable cause) {
        super(message, cause);
    }
}
