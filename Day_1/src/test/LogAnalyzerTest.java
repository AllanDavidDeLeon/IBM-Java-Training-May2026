package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import logfile_no2.LogAnalyzer;

class MalformedLogEntryException extends Exception {
    public MalformedLogEntryException(String msg) {
        super(msg);
    }
}

class LogAnalyzerTest {

	// Missing ]
	@Test
	void should_ReturnTrue_ifMissingBracket() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/resources/test1/server.log"};

	    Path expected =
	            Path.of("src/resources/test1/expected.txt");

	    String contentExpected =
	            Files.readString(expected, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual =
	            Path.of("src/resources/summary.txt");

	    String contentActual =
	            Files.readString(actual, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}
	
	// Missing Message
	@Test
	void should_Return_ifMissingMessage() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/resources/test2/server.log"};

	    Path expected =
	            Path.of("src/resources/test2/expected.txt");

	    String contentExpected =
	            Files.readString(expected, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual =
	            Path.of("src/resources/summary.txt");

	    String contentActual =
	            Files.readString(actual, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}
	
	// Missing Message
	@Test
	void should_Return_ifInvalidLogLevel() throws MalformedLogEntryException, IOException {

	    String[] args = {"src/resources/test3/server.log"};

	    Path expected =
	            Path.of("src/resources/test3/expected.txt");

	    String contentExpected =
	            Files.readString(expected, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    LogAnalyzer.main(args);

	    Path actual =
	            Path.of("src/resources/summary.txt");

	    String contentActual =
	            Files.readString(actual, StandardCharsets.UTF_8)
	                    .replace("\r\n", "\n")
	                    .replace("\r", "");

	    assertEquals(contentExpected, contentActual);
	}

	@Test
	void should_PrintSkippedLineMessage() {

		String[] args = {"src/resources/test4/server.log"};
		
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
	
	@Test
	void should_ReturnTrue_ifLogFileNotFound() {

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    LogAnalyzer.main(new String[]{
	            "src/resources/file.txt"
	    });

	    assertTrue(out.toString().contains("Log file not found."));
	}

	@Test
	void ioErrorReadingFile_fileLocked() throws Exception {

	    Path path = Paths.get("src/resources/test.log");

	    Files.writeString(path, "test data");

	    FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE);

	    FileLock lock = channel.lock();

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    LogAnalyzer.main(new String[]{"src/resources/test.log"});

	    assertTrue(out.toString().contains("Error reading file."));

	    lock.release();
	    channel.close();
	}
	
	@Test
	void ioErrorWritingFile() {
		
	    String input = "src/resources/test.log";

	    try {
	        Files.writeString(Path.of(input), "dummy");

	        Path bad = Path.of("src/resources/summary.txt");
	        
	        Files.delete(bad);
	            
	        Files.createDirectory(bad); 

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(out));
	        
	        LogAnalyzer.main(new String[]{input});
	        
	        assertTrue(out.toString().contains("Error writing summary file."));

	    } catch (Exception e) {

	    } finally {
	        try {
	            Path bad = Path.of("src/resources/summary.txt");

	            if (Files.isDirectory(bad)) {
	                Files.walk(bad).sorted(Comparator.reverseOrder()).forEach(p -> p.toFile().delete());
	            }

	        } catch (Exception ignored) {}
	    }
	}


}
