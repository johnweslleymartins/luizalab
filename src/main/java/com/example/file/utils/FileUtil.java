package com.example.file.utils;

import com.example.file.exception.FileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public List<File> getFileByPath(String path) {
        File folder = new File(path);
        ArrayList<File> files = new ArrayList<>();
        for (File file : folder.listFiles()) {
            files.add(file);
        }
        return files;
    }

    public void generateFileInPath(String json, String fileName, String diretorioSaida) {
        File file = new File(diretorioSaida + fileName + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileException("Erro ao criar um novo arquivo");
            }
        }

        try {
            BufferedWriter dados = new BufferedWriter(new FileWriter(file));
            dados.write(json);
            dados.close();
        } catch (IOException e) {
            throw new FileException("Erro ao escrever no arquivo");
        }
    }

    public void removeFileFromPath(String diretorioSaida) {
        List<File> files = getFileByPath(diretorioSaida);
        files.forEach(file -> file.delete());
    }
}