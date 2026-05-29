package com.ibm.training.day2;

public class Main {

	public static void main(String[] args) {
	
		
		Library library = new Library();
		
		library.addBook(new Book("Die Hard 2", "Nicholas Cage"));
		library.addBook(new Book("KFC", "Larry Sanders"));
		library.addBook(new Book("Flopper", "Shai Gilgeous-Alexander"));
		//library.addBook(new Book("Flopper", "Shai Gilgeous-Alexander"));

		System.out.println("======== Before Borrow ========");
		library.showAllBooks();
		System.out.println("===============================");
		library.borrowBook("Flopper");
		
		System.out.println("======== After Borrow ========");
		library.showAllBooks();
		System.out.println("===============================");

		library.returnBook("Flopper");
		
		System.out.println("======== After Return ========");
		library.showAllBooks();
		System.out.println("===============================");

		library.borrowBook("Flopper");
		//library.borrowBook("Flopper");
		library.returnBook("Flopper");
		library.returnBook("Flopper");
	}

}
