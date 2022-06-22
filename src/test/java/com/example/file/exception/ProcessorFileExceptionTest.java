package com.example.file.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProcessorFileExceptionTest {

    @Test
    public void ProcessorFileExceptionTest() {
        String messageError = "messageError";
        ProcessorFileException e = new ProcessorFileException(messageError);
        Assertions.assertEquals(e.getMessage(), messageError);
    }
}
