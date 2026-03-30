package com.example.BookMyShow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SeatMap {
    private final int showId;
    private final double priceMultiplier;
    private final Map<Integer, Seat> seats;

    public SeatMap(int showId, double priceMultiplier, Map<Integer, Seat> seats) {
        this.showId = showId;
        this.priceMultiplier = priceMultiplier;
        this.seats = new HashMap<>(seats);
    }

    public static SeatMap basic(int showId) {
        Map<Integer, Seat> generated = new HashMap<>();
        generated.put(1, new Seat(1, SeatType.ECONOMY, 150));
        generated.put(2, new Seat(2, SeatType.ECONOMY, 150));
        generated.put(3, new Seat(3, SeatType.DELUX, 250));
        generated.put(4, new Seat(4, SeatType.LUXURY, 350));
        generated.put(5, new Seat(5, SeatType.LUXURY, 350));
        return new SeatMap(showId, 1.0, generated);
    }

    public int getShowId() {
        return showId;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public Map<Integer, Seat> getSeats() {
        return Collections.unmodifiableMap(seats);
    }

    public Optional<Seat> getSeat(int seatId) {
        return Optional.ofNullable(seats.get(seatId));
    }
}
