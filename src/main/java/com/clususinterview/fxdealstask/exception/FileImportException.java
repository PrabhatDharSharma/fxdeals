package com.clususinterview.fxdealstask.exception;

public class FileImportException extends Exception {

    public FileImportException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FileImportException(String message) {
        super(message);
    }
}
