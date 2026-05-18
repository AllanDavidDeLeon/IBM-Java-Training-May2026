package day1;
import java.util.Scanner;


public class blackjack {
	
	public static int blackJack(int a, int b) {
		return (a > 21 && b > 21) ? 0 : (Math.abs(21 - a) < Math.abs(21 - b) ? a : b);

	}
	
	public static void dayOfWeek(int a) {
		switch (a) {
		case 1: System.out.println("Monday"); break;
		case 2: System.out.println("Tuesday"); break;
		case 3: System.out.println("Wednesday"); break;
		case 4: System.out.println("Thursday"); break;
		case 5: System.out.println("Friday"); break;
		case 6: System.out.println("Saturday"); break;
		case 7: System.out.println("Sunday"); break;
		default : System.out.println("Invalid day number"); break;


		}
	}
	
	enum Day { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	public static void dayOfWeekPattern(Day day) {
	    switch (day) {
	        case Monday -> System.out.println("Monday");
	        case Tuesday -> System.out.println("Tuesday");
	        case Wednesday -> System.out.println("Wednesday");
	        case Thursday -> System.out.println("Thursday");
	        case Friday -> System.out.println("Friday");
	        case Saturday -> System.out.println("Saturday");
	        case Sunday -> System.out.println("Sunday");
	        default -> System.out.println("Invalid day");
	    }
	}
	
	public static void pyramid(int a) {
		for (int i = 0; i <= a; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(blackJack(1,2));
		System.out.println(blackJack(21,22));
		System.out.println(blackJack(22,22));
		System.out.println(blackJack(2,10));
		
		System.out.print("Enter a number for day: ");
		int numDay = sc.nextInt();
		
		dayOfWeek(numDay);
		
		Day day = Day.Monday;
		dayOfWeekPattern(day);
		
		int num = 0;
		while (num > 20 || num < 1) {
			System.out.print("Enter a number for pyramid: ");
			num = sc.nextInt();
		}
		pyramid(num);
			
		do {
			System.out.print("Enter a number for pyramid: ");
			num = sc.nextInt();
		} while (num > 20 || num < 1);
		pyramid(num);
		
		sc.close();
	
	}

}
