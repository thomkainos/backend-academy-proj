package org.example.exception;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(Exception e) { super(e); }
}
