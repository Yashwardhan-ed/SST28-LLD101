package com.example.BookMyShow;

public class SimplePriceStrategy implements PriceStrategy {
    @Override
    public double calculatePrice(Seat seat, double priceMultiplier) {
        return seat.getBasePrice() * priceMultiplier;
    }
}
