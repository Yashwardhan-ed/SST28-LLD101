package com.example.BookMyShow;

import java.time.LocalDateTime;
import java.util.List;

public class Application {
	public static void main(String[] args) {
		InMemoryDataStore dataStore = new InMemoryDataStore();
		AdminService adminService = new AdminService(dataStore);
		CustomerService customerService = new CustomerService(dataStore, new MockPaymentGateway(), new SimplePriceStrategy());

		Movie inception = new Movie(1, "Inception", "English");
		adminService.addMovie(inception);

		Theater theater = new Theater(1, "Central Cineplex", "Bangalore");
		theater.addAuditorium(new Auditorium(101));
		adminService.addTheater(theater);

		Show eveningShow = new Show(10, inception.getMovieId(), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3), theater.getTheaterId(), 101);
		adminService.addShow(eveningShow);

		System.out.println("Available theaters: " + customerService.getTheaters("Bangalore"));
		System.out.println("Movies in theater: " + customerService.getMovies("Bangalore"));

		Ticket ticket = customerService.bookTicket(eveningShow.getShowId(), List.of(1, 2));
		if (ticket != null) {
			customerService.cancelTicket(ticket.getTicketId());
		}
	}
}
