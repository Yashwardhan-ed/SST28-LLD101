package com.example.BookMyShow;

import java.time.LocalDateTime;

public class Show {
	private final int showId;
	private final int movieId;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	private final int theaterId;
	private final int auditoriumId;

	public Show(int showId, int movieId, LocalDateTime startTime, LocalDateTime endTime, int theaterId, int auditoriumId) {
		this.showId = showId;
		this.movieId = movieId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.theaterId = theaterId;
		this.auditoriumId = auditoriumId;
	}

	public int getShowId() {
		return showId;
	}

	public int getMovieId() {
		return movieId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public int getAuditoriumId() {
		return auditoriumId;
	}
}
