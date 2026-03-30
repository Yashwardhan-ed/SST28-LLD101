package com.example.BookMyShow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ticket {
    private final int ticketId;
    private final int userId;
    private final int showId;
    private final List<Integer> seats;
    private final double totalAmount;
    private final BookingStatus status;
    private final String paymentId;

    public Ticket(int ticketId, int userId, int showId, List<Integer> seats, double totalAmount, BookingStatus status, String paymentId) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.showId = showId;
        this.seats = new ArrayList<>(seats);
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentId = paymentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public int getShowId() {
        return showId;
    }

    public List<Integer> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
