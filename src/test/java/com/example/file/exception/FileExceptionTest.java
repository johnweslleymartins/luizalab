package com.example.file.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileExceptionTest {

    @Test
    public void FileExceptionTest () {
        String messageError = "messageError";
        FileException e = new FileException(messageError);
        Assertions.assertEquals(e.getMessage(), messageError);
    }
}