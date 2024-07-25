package org.example.exception;

public class SqlException extends JobRoleDaoException {
    public SqlException(final String errorMessage) {
        super(errorMessage);
    }
}
