package com.example.BookMyShow;

public class AdminService implements AdminCapable {
	private final InMemoryDataStore dataStore;

	public AdminService(InMemoryDataStore dataStore) {
		this.dataStore = dataStore;
	}

	@Override
	public void addShow(Show show) {
		SeatMap seatMap = SeatMap.basic(show.getShowId());
		dataStore.addShow(show, seatMap);
		System.out.println("[Admin] Added show " + show.getShowId());
	}

	@Override
	public void addMovie(Movie movie) {
		dataStore.addMovie(movie);
		System.out.println("[Admin] Added movie " + movie.getTitle());
	}

	@Override
	public void addTheater(Theater theater) {
		dataStore.addTheater(theater);
		System.out.println("[Admin] Added theater " + theater.getName());
	}
}
