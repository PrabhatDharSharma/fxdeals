package com.clususinterview.fxdealstask.datavalidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileValidator {

    private FileValidator(){

    }

    private static Pattern fileExtensionRegex = Pattern.compile(".+\\.(?i)(txt|csv)$");

    public static boolean validateFileExtension(String fileName) {

        Matcher match = fileExtensionRegex.matcher(fileName);
        return match.matches();
    }
}
