package com.example.file.service;

import com.example.file.utils.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProcessorServiceTest {

    @InjectMocks
    private ProcessorService service;

    @Mock
    private FileUtil fileUtil;

    private static final String diretorioEntrada = "\\src\\main\\resources\\diretorioArquivoHomologacao\\entrada\\";
    private static final String diretorioSaida = "\\src\\main\\resources\\diretorioArquivoHomologacao\\saida\\";
    private static final String diretorioBase = System.getProperty("user.dir");

    @BeforeEach
    public void initTest() {
        this.service = new ProcessorService(diretorioEntrada, diretorioSaida);
        this.fileUtil = new FileUtil();
    }

    @Test
    public void startProcessorTest() {
        service.startProcessor();
        fileUtil.createDirectoriesByPath(diretorioBase + diretorioSaida);
        List<File> files = fileUtil.getFileByPath(diretorioBase + diretorioSaida);
        Assertions.assertEquals(2, files.size());

        fileUtil.removeFileFromPath(diretorioBase + diretorioSaida);
        files = fileUtil.getFileByPath(diretorioBase + diretorioSaida);
        Assertions.assertEquals(0, files.size());
    }
}