package com.example.BookMyShow;

public class MockPaymentGateway implements PaymentGateway {
    @Override
    public boolean makePayment(double amount) {
        System.out.println("[PaymentGateway] Charging amount: " + amount);
        return true;
    }
}
