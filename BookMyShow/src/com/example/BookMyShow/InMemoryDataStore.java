package com.example.BookMyShow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryDataStore {
    private final Map<Integer, Movie> movies = new HashMap<>();
    private final Map<Integer, Theater> theaters = new HashMap<>();
    private final Map<Integer, Show> shows = new HashMap<>();
    private final Map<Integer, Ticket> tickets = new HashMap<>();
    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final Map<Integer, TicketCancellation> cancellations = new HashMap<>();
    private final AtomicInteger ticketIdSequence = new AtomicInteger(1);
    private final AtomicInteger bookingIdSequence = new AtomicInteger(1);

    public void addMovie(Movie movie) {
        movies.put(movie.getMovieId(), movie);
    }

    public void addTheater(Theater theater) {
        theaters.put(theater.getTheaterId(), theater);
    }

    public void addShow(Show show, SeatMap seatMap) {
        shows.put(show.getShowId(), show);
        Theater theater = theaters.get(show.getTheaterId());
        if (theater != null) {
            theater.registerShow(show, seatMap);
        }
    }

    public Optional<Show> getShow(int showId) {
        return Optional.ofNullable(shows.get(showId));
    }

    public Optional<SeatMap> getSeatMap(int showId) {
        return theaters.values().stream()
                .map(theater -> theater.seatMapForShow(showId).orElse(null))
                .filter(Objects::nonNull)
                .findFirst();
    }

    public int nextTicketId() {
        return ticketIdSequence.getAndIncrement();
    }

    public int nextBookingId() {
        return bookingIdSequence.getAndIncrement();
    }

    public void saveTicket(Ticket ticket) {
        tickets.put(ticket.getTicketId(), ticket);
    }

    public void saveBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public void saveCancellation(TicketCancellation cancellation) {
        cancellations.put(cancellation.getTicketId(), cancellation);
    }

    public Optional<Ticket> findTicket(int ticketId) {
        return Optional.ofNullable(tickets.get(ticketId));
    }

    public List<Theater> findTheaters(String city) {
        return theaters.values().stream()
                .filter(theater -> theater.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Movie> findMovies(String city) {
        return theaters.values().stream()
                .filter(theater -> theater.getCity().equalsIgnoreCase(city))
                .flatMap(theater -> theater.getMovieIds().stream())
                .map(movies::get)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}
