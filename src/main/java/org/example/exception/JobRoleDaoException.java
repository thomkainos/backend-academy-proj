package org.example.exception;

public class JobRoleDaoException extends Exception {
    public JobRoleDaoException(final String errorMessage, final Exception e) {
        super(errorMessage, e);
    }
}
