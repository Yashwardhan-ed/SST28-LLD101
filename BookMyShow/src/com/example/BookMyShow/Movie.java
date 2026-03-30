package com.example.BookMyShow;

public class Movie {
	private final int movieId;
	private final String title;
	private final String language;

	public Movie(int movieId, String title, String language) {
		this.movieId = movieId;
		this.title = title;
		this.language = language;
	}

	public int getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public String toString() {
		return "Movie{" +
				"movieId=" + movieId +
				", title='" + title + '\'' +
				", language='" + language + '\'' +
				'}';
	}
}
