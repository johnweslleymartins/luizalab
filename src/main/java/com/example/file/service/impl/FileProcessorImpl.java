package com.example.file.service.impl;

import com.example.file.service.FileProcessor;
import com.example.file.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileProcessorImpl implements FileProcessor {

    @Autowired
    private ProcessorService service;

    @Override
    public void startProcessor() {
        service.startProcessor();
    }
}