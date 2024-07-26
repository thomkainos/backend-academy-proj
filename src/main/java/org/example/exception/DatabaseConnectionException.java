package org.example.exception;

public class DatabaseConnectionException extends JobRoleDaoException {
    public DatabaseConnectionException(final String errorMessage,
                                       final Exception e) {
        super(errorMessage, e);
    }
}
