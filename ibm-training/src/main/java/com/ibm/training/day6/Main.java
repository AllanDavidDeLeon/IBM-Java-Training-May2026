package com.ibm.training.day6;


import java.util.Scanner;

enum Choice {A, V, U, D, Q}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    	System.out.println("=== MENU ===");
    	System.out.println("[A]dd");
    	System.out.println("[V]iew");
    	System.out.println("[U]pdate Password");
    	System.out.println("[D]elete");
    	System.out.println("[Q]uit");
    	
        Choice choice = null;
        
        while (choice == null) {
        	System.out.print("Enter Choice: ");

            String input = scanner.nextLine().trim().toUpperCase();
            try {
                choice = Choice.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
        
        switch (choice) {
        case A:
            StudentFunctions.addStudent();
            break;
        case V:
            StudentFunctions.viewStudents();
            break;
        case U:
            StudentFunctions.updatePassword();
            break;
        case D:
            StudentFunctions.deleteStudent();
            break;
        case Q:
            
            return;
        }

    }
}