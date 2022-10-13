package com.nokia.suply.task.suplytask.exception;

public class InvalidCommandException extends  RuntimeException {

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
