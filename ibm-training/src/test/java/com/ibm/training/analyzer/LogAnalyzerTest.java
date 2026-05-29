package com.ibm.training.analyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LogAnalyzerTest {
	
	LogAnalyzer analyzer = new LogAnalyzer();

	
	/**
	 * Deletes summary file before each test.
	 */
	@BeforeEach
	void setUp() throws IOException {
	    Files.deleteIfExists(Path.of("src/test/resources/summary.txt"));
	}
	
	/** 
	 * Tests handling of missing [,] in files
	 * Compares generated output with expected file
	 */ 
	@Test
	void exec001() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/test/resources/analyzer/exec001/server.log"};

	    Path expected = Path.of("src/test/resources/analyzer/exec001/expected.txt");

	    String contentExpected =Files.readString(expected, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual = Path.of("src/test/resources/summary.txt");

	    String contentActual = Files.readString(actual, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}
	
	/** 
	 * Tests handling of missing message in server.log
	 * Compares generated output with expected file
	 */
	@Test
	void exec002() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/test/resources/analyzer/exec002/server.log"};

	    Path expected = Path.of("src/test/resources/analyzer/exec002/expected.txt");

	    String contentExpected = Files.readString(expected, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual = Path.of("src/test/resources/summary.txt");

	    String contentActual = Files.readString(actual, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}
	
	/** 
	 * Tests handling of invalid log level
	 * Compares generated output with expected file
	 */
	@Test
	void exec003() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/test/resources/analyzer/exec003/server.log"};

	    Path expected = Path.of("src/test/resources/analyzer/exec003/expected.txt");

	    String contentExpected = Files.readString(expected, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual = Path.of("src/test/resources/summary.txt");

	    String contentActual = Files.readString(actual, StandardCharsets.UTF_8).replace("\r\n", "\n").replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}
	
	/** 
	 * Tests handling of malformed line error
	 * Compares generated output with expected file
	 */
	@Test
	void exec004() {

	    String[] args = {"src/test/resources/analyzer/exec004/server.log"};

		
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    PrintStream original = System.out;

	    System.setOut(new PrintStream(out));

	    try {
	        LogAnalyzer.main(args);
	    } finally {
	        System.setOut(original);
	    }

	    String actual = out.toString();

	    assertTrue(actual.contains(
	            "Skipping malformed line: [2024-05-10 09:02:00] 67: Authentication successful for 'user123'"
	    ));
	}
	
	/** 
	 * Tests handling of file not found
	 * Asserts true if a specific string contains in the console
	 */
	@Test
	void exec005() {

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    LogAnalyzer.main(new String[]{"src/test/resources/file.txt"});

	    assertTrue(out.toString().contains("Log file not found."));
	}
	
	/** 
	 * Tests handling of error reading file
	 * Asserts true if a specific string contains in the console
	 */
	@Test
	void exec006() throws Exception {

	    Path path = Paths.get("src/test/resources/test.log");

	    Files.writeString(path, "test data");

	    FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE);

	    FileLock lock = channel.lock();

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    LogAnalyzer.main(new String[]{"src/test/resources/test.log"});

	    assertTrue(out.toString().contains("Error reading file."));

	    lock.release();
	    channel.close();
	}
	
	/** 
	 * Tests handling of error writing file
	 * Asserts true if a specific string contains in the console
	 */
	@Test
	void exec007() {
		
	    String input = "src/test/resources/test.log";

	    try {
	        Files.writeString(Path.of(input), "dummy");

	        Path bad = Path.of("src/test/resources/summary.txt");
	        
	        Files.createDirectory(bad); 

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(out));
	        
	        LogAnalyzer.main(new String[]{input});
	        
	        assertTrue(out.toString().contains("Error writing summary file."));
	        

	    } catch (Exception e) {

	    }
	}


}
