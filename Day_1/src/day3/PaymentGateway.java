package day3;

public non-sealed class PaymentGateway extends Gateway {

	@Override
	public void processPayment(Payment payment) {
		payment.executePayment();
		System.out.println("Payment has been processed.");
	}

	
}
