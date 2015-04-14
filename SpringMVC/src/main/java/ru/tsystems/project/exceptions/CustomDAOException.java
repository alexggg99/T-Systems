package ru.tsystems.project.exceptions;

/**
 * A custom exception to be thrown.
 */
public class CustomDAOException extends RuntimeException {
    public CustomDAOException(String message)
    {

    }
    public CustomDAOException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
