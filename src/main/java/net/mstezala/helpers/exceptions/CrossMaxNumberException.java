package net.mstezala.helpers.exceptions;

/**
 *
 */
public class CrossMaxNumberException extends Exception {

    /**
     * Constructs a new exception with detail message. It should thrown when @code {Slot} max Limit has crossed.
     *
     * @param message the detail message. It's was show when getMessage() from super class is calling.
     */
    public CrossMaxNumberException(String message) {
        super(message);
    }
}
