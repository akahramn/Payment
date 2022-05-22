package com.akahraman.server.payment.exceptions;

/**
 *InvalidMoneyAmount is throwing message if to enter invalid money amount.
 */
public class InvalidMoneyAmount extends RuntimeException {

    public InvalidMoneyAmount(String message, Throwable cause) {
        super(message, cause);
    }
}
