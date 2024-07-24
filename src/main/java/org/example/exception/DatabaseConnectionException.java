package org.example.exception;

public class DatabaseConnectionException extends JobRoleDaoException {
    public DatabaseConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
