package day6;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentFunctions {
	
	public static void addStudent() {

	    String sql = "INSERT INTO student (studentid, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?)";

	    try (Scanner scanner = new Scanner(System.in);
	        Connection con = DBConnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)) {
	    	System.out.println("=== Add Student ===");
	        System.out.print("ID: ");
	        int studentid = Integer.parseInt(scanner.nextLine());

	        System.out.print("First Name: ");
	        String firstname = scanner.nextLine();

	        System.out.print("Last Name: ");
	        String lastname = scanner.nextLine();

	        System.out.print("Email: ");
	        String email = scanner.nextLine();

	        System.out.print("Password: ");
	        String password = scanner.nextLine();

	        ps.setInt(1, studentid);
	        ps.setString(2, firstname);
	        ps.setString(3, lastname);
	        ps.setString(4, email);
	        ps.setString(5, password);

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Student added successfully!");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public static void viewStudents() {

        String sql = "SELECT * FROM student";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("=== View Students ===");

            while (rs.next()) {
                int id = rs.getInt("studentid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String password = rs.getString("password");
                System.out.println(id + " | " + firstname + " | " + lastname + " | " + password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updatePassword() {
        String sql = "UPDATE student SET password = ? WHERE studentid = ?";

        try (Scanner scanner = new Scanner(System.in);
             Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter student ID: ");
            
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new password: ");
            String password = scanner.nextLine().trim();


            ps.setString(1, password);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("No student found with ID: " + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteStudent() {
        String sql = "DELETE FROM student WHERE studentid = ?";

        try (Scanner scanner = new Scanner(System.in);
             Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter student ID to delete: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. ID must be a number.");
                return;
            }
            int studentid = scanner.nextInt();
            ps.setInt(1, studentid);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student with ID " + studentid + " deleted successfully.");
            } else {
                System.out.println("No student found with ID " + studentid);
            }

        } catch (Exception e) {
            System.out.println("Error deleting student:");
            e.printStackTrace();
        }
    }


}
