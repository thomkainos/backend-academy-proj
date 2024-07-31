package org.example.exception;

public class AuthDaoException extends Exception {
    public AuthDaoException(final String errorMessage, final Exception e) {
        super(errorMessage, e);
    }
}
