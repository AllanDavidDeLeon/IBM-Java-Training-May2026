package day5;
import java.io.*;
import java.util.*;

public class StudentFile {

	
	public static void main(String[] args) {
		
	
		try (
				BufferedReader br = new BufferedReader(new FileReader("src/day5/students.csv"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("src/day5/student.json"));
		) {
			String line;
			boolean first = true;
			br.readLine();
			bw.write("[");
			bw.newLine();
			while ((line = br.readLine()) != null) {
				
				String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String course = values[2];
                
                if (!first) {
                    bw.write(",");
                    bw.newLine();
                }
                
                bw.write("  {");
                bw.newLine();
                bw.write("   \"id\": \"" + id + "\",");
                bw.newLine();
                bw.write("   \"name:\" " + name + ",");
                bw.newLine();
                bw.write("   \"course:\" " + course);
                bw.newLine();
                bw.write("  }");

                first = false;
			}
            bw.newLine();
            bw.write("]");
		} catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
	}
}


