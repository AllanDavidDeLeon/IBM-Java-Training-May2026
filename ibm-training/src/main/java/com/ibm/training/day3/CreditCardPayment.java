package com.ibm.training.day3;

public class CreditCardPayment extends Payment implements Verifiable{

	private String cardNumber;
	
	public CreditCardPayment(double amount, String cardNumber) {
		super(amount);
		this.cardNumber = cardNumber;
	}

	@Override
	public boolean verify() {
		return cardNumber.length() == 16;
	}

	@Override
	public void executePayment() {
		System.out.println("Processing credit card payment...");
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	
	

}
