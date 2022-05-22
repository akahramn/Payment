package com.akahraman.server.payment.exceptions;

/**
 * InvalidBalanceLimitException is throwing message if account balance limit is not sufficient.
 */
public class InvalidBalanceLimitException extends RuntimeException{
    public InvalidBalanceLimitException(String message) {
        super(message);
    }
}
