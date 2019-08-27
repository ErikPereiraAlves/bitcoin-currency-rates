package com.alveser.bpi.exception;



/**
 * 
 * @author erikp
 * 
 * Application`s custom exception class.
 *
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -3681636607322599994L;

    public ApplicationException(String message) {
        super(message);
    }
}
  