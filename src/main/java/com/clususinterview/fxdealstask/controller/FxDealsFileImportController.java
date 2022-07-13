package com.clususinterview.fxdealstask.controller;

import com.clususinterview.fxdealstask.exception.DuplicateFileException;
import com.clususinterview.fxdealstask.exception.FileImportException;
import com.clususinterview.fxdealstask.service.FxDealFileImportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FxDealsFileImportController {

    @Autowired
    FxDealFileImportServiceImpl fxDealFileImportService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FxDealsFileImportController.class);

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFxFile(@RequestParam("file") MultipartFile multipartFile) throws DuplicateFileException, FileImportException {

        if (multipartFile == null) {
            LOGGER.error("No file received in the request!");
            return new ResponseEntity<>("No file was uploaded!", HttpStatus.BAD_REQUEST);
        }

        String originalFileName = multipartFile.getOriginalFilename();

        LOGGER.info("[uploadFxFile()] Loading file {}...", originalFileName);

        if (fxDealFileImportService.isFileAlreadyImported(originalFileName)) {
            throw new DuplicateFileException(originalFileName);
        } else {
            LOGGER.info("[uploadFxFile()] Importing file {}...", originalFileName);
            fxDealFileImportService.uploadFile(multipartFile);
        }

        LOGGER.info("[uploadFxFile()] Successfully imported file {}...", originalFileName);

        return new ResponseEntity<>("Successfully imported " + multipartFile.getOriginalFilename(), HttpStatus.OK);
    }

}
