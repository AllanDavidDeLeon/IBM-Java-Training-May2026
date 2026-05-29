package com.ibm.training.day2;

import java.util.ArrayList;


public class Library {
	private ArrayList<Book> books;
	
	public Library() {
		this.books = new ArrayList<>();
	}
	
	
	public void addBook (Book b) {
		for (Book book: books) {
			if (book.getTitle() == b.getTitle() && book.getAuthor() == b.getAuthor()) {
				throw new IllegalArgumentException("Book has already been added.");
			}
		}
		books.add(b);
	}
	
	public void showAllBooks() {
		for (Book book: books) {
			book.getInfo();
		}
	}
	
	public void borrowBook(String title) {
		for (Book book: books) {
			if (book.getTitle() == title) {
				book.borrowBook();
			}
		}
	}

	public void returnBook(String title) {
		for (Book book: books) {
			if (book.getTitle() == title) {
				book.returnBook();
			}
		}
	}
}
