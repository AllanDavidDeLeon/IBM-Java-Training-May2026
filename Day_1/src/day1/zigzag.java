package day1;
import java.util.Scanner;

public class zigzag {

	public static void zigZag(int a) {

	    for (int i = 1; i <= a; i++) {

	        int start = (i - 1) * a + 1;
	        int end = i * a;

	        if (i % 2 == 0) {

	            for (int j = end; j >= start; j--) {
	                System.out.print(j + " ");
	            }

	        } else {

	            for (int j = start; j <= end; j++) {
	                System.out.print(j + " ");
	            }
	        }

	        System.out.println();
	    }
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a number for zigzag: ");
		int number = sc.nextInt();
		
		zigZag(number);
		
		sc.close();
	}

}
