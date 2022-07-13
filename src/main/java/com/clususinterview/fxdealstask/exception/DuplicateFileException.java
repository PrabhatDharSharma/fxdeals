package com.clususinterview.fxdealstask.exception;

public class DuplicateFileException extends Exception {

    public DuplicateFileException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DuplicateFileException(String message) {
        super(message);
    }
}
