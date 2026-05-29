package com.ibm.training.day3;

public class PaypalPayment extends Payment implements Verifiable{

	private String email;
	
	public PaypalPayment(double amount, String email) {
		super(amount);
		this.email = email;
	}

	@Override
	public boolean verify() {
		return email.contains("@") ? true : false;
	}

	@Override
	public void executePayment() {
		System.out.println("Processing Paypal payment...");
	}
	
}
