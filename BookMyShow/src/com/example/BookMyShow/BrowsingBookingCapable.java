package com.example.BookMyShow;

import java.util.List;

public interface BrowsingBookingCapable {
    List<Theater> getTheaters(String city);

    List<Movie> getMovies(String city);

    Ticket bookTicket(int showId, List<Integer> seatIds);

    TicketCancellation cancelTicket(int ticketId);
}
