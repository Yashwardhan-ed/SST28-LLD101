package com.example.BookMyShow;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Auditorium {
    private final int auditoriumId;
    private final Map<Integer, SeatMap> screening = new HashMap<>();

    public Auditorium(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void addSeatMap(int showId, SeatMap seatMap) {
        screening.put(showId, seatMap);
    }

    public Optional<SeatMap> getSeatMap(int showId) {
        return Optional.ofNullable(screening.get(showId));
    }
}
