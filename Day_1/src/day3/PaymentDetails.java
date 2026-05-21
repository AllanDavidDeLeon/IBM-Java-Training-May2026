package day3;
import java.time.LocalDateTime;

public record PaymentDetails(int transactionId, double amount, String paymentMethod, LocalDateTime date) {

}
