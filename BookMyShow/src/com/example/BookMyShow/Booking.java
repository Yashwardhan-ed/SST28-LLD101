package com.example.BookMyShow;

public class Booking {
    private final int bookingId;
    private final int userId;
    private BookingStatus bookingStatus;

    public Booking(int bookingId, int userId, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.bookingStatus = bookingStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
