package com.ibm.training.day2;

public class Book {

	
	private String title;
	private String author;
	private boolean available;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void getInfo() {
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		if (available) {
			System.out.println(title + " is available.");
		} else {
			System.out.println(title + " is not available.");
		}
	}
	
	public void borrowBook() {
		if (available) {
			available = false;
			System.out.println(title + " is successfully borrowed.");
		} else {
            throw new IllegalArgumentException(title + " is not available as of the moment");
		}
	}
	
	public void returnBook() {
		if (available) {
            throw new IllegalArgumentException(title + " is returned already.");
		} else {
			available = true;
			System.out.println(title + " is successfully returned.");
		}
	}


}
