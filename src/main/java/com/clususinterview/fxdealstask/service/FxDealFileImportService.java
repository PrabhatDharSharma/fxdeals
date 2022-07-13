package com.clususinterview.fxdealstask.service;

import com.clususinterview.fxdealstask.exception.FileImportException;
import org.springframework.web.multipart.MultipartFile;

public interface FxDealFileImportService {

    void uploadFile(MultipartFile multipartFile) throws FileImportException;

    boolean isFileAlreadyImported(String sourceFileName);
}
