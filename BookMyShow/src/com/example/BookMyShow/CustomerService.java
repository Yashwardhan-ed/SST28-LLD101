package com.example.BookMyShow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerService implements BrowsingBookingCapable {
    private final InMemoryDataStore dataStore;
    private final PaymentGateway paymentGateway;
    private final PriceStrategy priceStrategy;

    public CustomerService(InMemoryDataStore dataStore, PaymentGateway paymentGateway, PriceStrategy priceStrategy) {
        this.dataStore = dataStore;
        this.paymentGateway = paymentGateway;
        this.priceStrategy = priceStrategy;
    }

    @Override
    public List<Theater> getTheaters(String city) {
        return dataStore.findTheaters(city);
    }

    @Override
    public List<Movie> getMovies(String city) {
        return dataStore.findMovies(city);
    }

    @Override
    public Ticket bookTicket(int showId, List<Integer> seatIds) {
        System.out.println("[Customer] Trying to book show " + showId + " for seats " + seatIds);
        Optional<Show> showOpt = dataStore.getShow(showId);
        Optional<SeatMap> seatMapOpt = dataStore.getSeatMap(showId);
        if (showOpt.isEmpty() || seatMapOpt.isEmpty()) {
            System.out.println("[Customer] Show not found or seating not configured");
            return null;
        }

        SeatMap seatMap = seatMapOpt.get();
        List<Seat> seats = seatIds.stream()
                .map(id -> seatMap.getSeat(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();

        if (seats.size() != seatIds.size()) {
            System.out.println("[Customer] Some seats are invalid for this show");
            return null;
        }

        boolean alreadyBooked = seats.stream().anyMatch(seat -> seat.getSeatStatus() == SeatStatus.BOOKED);
        if (alreadyBooked) {
            System.out.println("[Customer] One or more seats are already booked");
            return null;
        }

        seats.forEach(seat -> seat.setSeatStatus(SeatStatus.BOOKED));
        double totalAmount = seats.stream()
                .mapToDouble(seat -> priceStrategy.calculatePrice(seat, seatMap.getPriceMultiplier()))
                .sum();

        boolean paymentSucceeded = paymentGateway.makePayment(totalAmount);
        int bookingId = dataStore.nextBookingId();
        Booking booking = new Booking(bookingId, 0, paymentSucceeded ? BookingStatus.CONFIRMED : BookingStatus.FAILED);
        dataStore.saveBooking(booking);

        if (!paymentSucceeded) {
            seats.forEach(seat -> seat.setSeatStatus(SeatStatus.PENDING));
            System.out.println("[Customer] Payment failed, booking rolled back");
            return null;
        }

        int ticketId = dataStore.nextTicketId();
        Ticket ticket = new Ticket(ticketId, 0, showId, seatIds, totalAmount, BookingStatus.CONFIRMED, "PAY-" + ticketId);
        dataStore.saveTicket(ticket);
        System.out.println("[Customer] Booking confirmed. Ticket: " + ticketId + ", amount: " + totalAmount);
        return ticket;
    }

    @Override
    public TicketCancellation cancelTicket(int ticketId) {
        Optional<Ticket> ticketOpt = dataStore.findTicket(ticketId);
        if (ticketOpt.isEmpty()) {
            System.out.println("[Customer] Ticket not found for cancellation");
            return null;
        }

        Ticket ticket = ticketOpt.get();
        Optional<SeatMap> seatMapOpt = dataStore.getSeatMap(ticket.getShowId());
        seatMapOpt.ifPresent(seatMap -> ticket.getSeats().forEach(seatId -> seatMap.getSeat(seatId).ifPresent(seat -> seat.setSeatStatus(SeatStatus.PENDING))));

        double refundAmount = ticket.getTotalAmount() * 0.8;
        TicketCancellation cancellation = new TicketCancellation(ticketId, refundAmount, LocalDateTime.now(), RefundStatus.COMPLETED);
        dataStore.saveCancellation(cancellation);
        System.out.println("[Customer] Ticket " + ticketId + " cancelled. Refund: " + refundAmount);
        return cancellation;
    }
}
