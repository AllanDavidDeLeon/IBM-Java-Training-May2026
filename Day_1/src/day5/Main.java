package day5;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws MalformedLogEntryException {

        HashMap<String, Integer> logCount = new HashMap<>();

        logCount.put("INFO", 0);
        logCount.put("WARN", 0);
        logCount.put("ERROR", 0);
        logCount.put("TOTAL", 0);

        List<String> errors = new ArrayList<>();
        List<LocalDateTime> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (
            BufferedReader br = new BufferedReader(new FileReader("src/day5/server.log"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/day5/summary.txt"))
        ) {
            String line;

            while ((line = br.readLine()) != null) {
            	LogParser.validate(line);
            	
            	String[] splits = line.split("]", 2);
            	String dateSplit = splits[0].replace("[", "").trim();
            	String extra = splits[1].trim();
            	
            	
            	LocalDateTime date = LocalDateTime.parse(dateSplit, formatter);
            	dates.add(date);
            	String[] levelSplit = extra.split(":", 2);
            	String level = levelSplit[0].trim();
                String message = levelSplit[1].trim();
                    
                if (level.equals("INFO")) {
                	logCount.computeIfPresent("INFO", (k, v) -> v + 1);
                } else if (level.equals("WARN")) {
                	logCount.computeIfPresent("WARN", (k, v) -> v + 1);
                } else if (level.equals("ERROR")) {
                	logCount.computeIfPresent("ERROR", (k, v) -> v + 1);
                    errors.add(message);
                }
                logCount.computeIfPresent("TOTAL", (k, v) -> v + 1);  
            }
            
            LocalDateTime early = Collections.min(dates);
            LocalDateTime late = Collections.max(dates);
            
            String earliest = early.format(formatter);
            String latest = late.format(formatter);
            
            System.out.println("INFO: " + logCount.get("INFO"));
            System.out.println("WARN: " + logCount.get("WARN"));
            System.out.println("ERROR: " + logCount.get("ERROR"));
            
            System.out.println("Earliest: " + earliest);
            System.out.println("Latest:   " + latest);
            
            bw.write("Log Summary Report");
            bw.newLine();
            bw.write("--------------------");
            bw.newLine();
            bw.write("Total Entries: " + logCount.get("TOTAL"));
            bw.newLine();
            bw.write("INFO: " + logCount.get("INFO"));
            bw.newLine();
            bw.write("WARN: " + logCount.get("WARN"));
            bw.newLine();
            bw.write("ERROR: " + logCount.get("ERROR"));
            bw.newLine();
            bw.newLine();
            bw.write("Error Messages:");
            bw.newLine();
            for (String e : errors) {
            	bw.write("- " + e);
            	bw.newLine();
            }
            bw.newLine();
            bw.write("Earliest Timestamp: " + earliest);
            bw.newLine();
            bw.write("Latest Timestamp:   " + latest);
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

}
