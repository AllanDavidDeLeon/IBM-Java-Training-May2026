package day3;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		List<Payment> payments = new ArrayList<>();
		
		payments.add(new CreditCardPayment(120, "1234567890123456"));
		payments.add(new PaypalPayment (420, "victor.wembanyama@spursin6.com"));
		payments.add(new BankTransferPayment (67, "1234567890"));
		
		//Errors
		payments.add(new BankTransferPayment (676767, "12345"));
		payments.add(new PaypalPayment (420, "sgaflopperbro"));
		payments.add(new CreditCardPayment(120, "12345678901234567"));
		
		PaymentType online = new OnlinePaymentType("Online Payment");
		PaymentType offline = new OfflinePaymentType("Offline Payment");
		
		Gateway gateway = new PaymentGateway();
		
		List <PaymentDetails> successfulPayments = new ArrayList<>();
		
		int transactionId = 0;
		
		for (Payment payment : payments) {
			
			payment.displayAmount();
			
			Verifiable verifiable = (Verifiable) payment;
			
			if (verifiable.verify()) {
				gateway.processPayment(payment);
				transactionId++;
				PaymentDetails paymentDetails = new PaymentDetails(transactionId, payment.getAmount(), payment.getClass().getSimpleName(), LocalDateTime.now());
				
				successfulPayments.add(paymentDetails);
			} else {
				System.out.println("Verificaton Failed.");
			}
			


		}
		
		System.out.println("=========================");

		System.out.println("Successful Payments:");
		
		for (PaymentDetails details : successfulPayments) {
			System.out.println("---------------");
			System.out.println("Transaction ID: " + details.transactionId());
			System.out.println("Amount: " + details.amount());
			System.out.println("Payment Method: " + details.paymentMethod());
			System.out.println("Date: " + details.date());

		}
	}
	
	

}
