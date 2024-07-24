package org.example.exception;

public class SqlException extends JobRoleDaoException {
    public SqlException(String errorMessage) {
        super(errorMessage);
    }
}
