package com.clususinterview.fxdealstask.exceptionhandler;


import com.clususinterview.fxdealstask.exception.DuplicateFileException;
import com.clususinterview.fxdealstask.exception.FileImportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FileImportException.class)
    public ResponseEntity<String> handleFileImportException(Exception exception) {

        LOGGER.error(exception.getMessage(), exception);

        return new ResponseEntity<>("Unable to import file!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateFileException.class)
    public ResponseEntity<String> handleDuplicateException(Exception exception) {

        LOGGER.error("[uploadFxFile()] The file {} has already been imported in the system.", exception.getMessage());

        return new ResponseEntity<>("Duplicate file received!", HttpStatus.BAD_REQUEST);

    }

}
