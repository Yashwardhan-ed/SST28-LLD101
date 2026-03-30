package com.example.BookMyShow;

public interface PriceStrategy {
    double calculatePrice(Seat seat, double priceMultiplier);
}
