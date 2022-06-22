package com.example.file;

import com.example.file.service.FileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FileLuizaLabApplication implements CommandLineRunner {

	@Autowired
	private FileProcessor processor;

	public static void main(String... args) {
		log.info("INICIANDO EXECUÇÃO");
		long start = System.currentTimeMillis();
		SpringApplication.run(FileLuizaLabApplication.class, args).close();
		long elapsed = System.currentTimeMillis() - start;
		long elapsedInSeconds = elapsed / 1000;
		log.info(String.format("ENCERRANDO A EXECUÇÃO EM %d SEGUNDOS ", elapsedInSeconds));
	}

	@Override
	public void run(String... args) throws Exception {
		processor.startProcessor();
	}
}