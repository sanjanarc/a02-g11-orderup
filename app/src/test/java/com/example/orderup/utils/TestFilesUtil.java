package com.example.orderup.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import com.example.orderup.logic.Services;



public class TestFilesUtil {
    private static final File DB_SRC = new File("src/main/assets/db/DB.script");

    public static File copyDB() throws IOException {
        final File target = File.createTempFile("temp-db", ".script");
        Files.copy(DB_SRC, target);
        Services.setDBPathName(target.getAbsolutePath().replace(".script", ""));
        return target;
    }

}
