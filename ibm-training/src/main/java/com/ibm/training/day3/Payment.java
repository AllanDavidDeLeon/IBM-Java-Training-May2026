package com.ibm.training.day3;

public abstract class Payment {
	private double amount;
	
	public Payment (double amount) {
		this.amount = amount;
	}
	
	public abstract void executePayment();
	
	public void displayAmount() {
		System.out.println("---------------");
		System.out.println("Amount: Php " + amount);
	}
	
	public double getAmount() {
		return amount;
	}
	
	
}
