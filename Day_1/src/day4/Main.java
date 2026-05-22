package day4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 50000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 52000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        
        Set<String> names = new HashSet<>();
        List<Employee> unique = new ArrayList<>();
        
        for (Employee e : employees) {
        	if(!names.contains(e.getName())) {
        		names.add(e.getName());
        		unique.add(e);
        	}
        }
        
        for (Employee e : unique) {
        	System.out.println(e.toString());
        }
        
        HashMap<String, List<Employee>> departments = new HashMap<>();
        
        for (Employee e : employees) {
        	
        	String dept = e.getDepartment();
        	
        	if(!departments.containsKey(dept)) {
        		departments.put(dept,  new ArrayList<Employee>());
        	}
    		departments.get(dept).add(e);
        }
        
       for (String dept : departments.keySet()) {
    	   System.out.println(dept + ": ");
    	   
    	   ArrayList<Employee> list = (ArrayList<Employee>) departments.get(dept);
    	   
    	   for (Employee e : list) {
    		   System.out.println(e.getName() + " - " + e.getSalary());
    	   }
    	   System.out.println();
       }
        
       System.out.println("======================");

       HashMap<String, Employee> highestPaidEmployee = new HashMap<>();
       
       for (Employee e : employees) {
    	   String dept = e.getDepartment();
    	   
    	   if(!highestPaidEmployee.containsKey(dept)) {
    		   highestPaidEmployee.put(dept,  e);
    		   }
    	   else {
    		   Employee currentHighest = highestPaidEmployee.get(dept);
    		   if (e.getSalary() > currentHighest.getSalary()) {
    			   highestPaidEmployee.put(dept, e);
    		   }
    	   }
       	
       }
       
       for (String dept : highestPaidEmployee.keySet()) {
    	   System.out.println("Highest paid employee in " + dept + ": ");
    	   
    	   Employee e = highestPaidEmployee.get(dept);
    	   System.out.println(e.getName() + " | " + e.getSalary());
       }
       
       
       

       employees.sort((a,b) -> Double.compare(b.getSalary(), a.getSalary()));
       
       
       System.out.println("======= Sorted Salaries ========");
       Set<Double> sortedSalaries = new HashSet<>();
       
       for (Employee e : employees) {
    	   sortedSalaries.add(e.getSalary());
    	   System.out.println(e.getName() + " | " + e.getSalary());
       }
       
       System.out.println("======= Unique Salaries ========");

       
       Set<Double> uniqueSalaries = new TreeSet<>();
       
       for (Employee e : employees) {
    	   uniqueSalaries.add(e.getSalary());
    	   System.out.println("Php " + e.getSalary());
       }
        
	}

}