package com.ibm.training.day3;

public class BankTransferPayment extends Payment implements Verifiable {

	
	private String accountNumber;
	
	public BankTransferPayment(double amount, String accountNumber) {
		super(amount);
		this.accountNumber = accountNumber;
	}

	@Override
	public boolean verify() {
		return accountNumber.length() == 10 ? true : false;

	}

	@Override
	public void executePayment() {
		System.out.println("Processing bank transfer...");
	}
	
}
