package com.example.file;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class FileLuizaLabApplicationTests {

	@Test
	public void test() { assertDoesNotThrow(() -> FileLuizaLabApplication.main("")); }
}