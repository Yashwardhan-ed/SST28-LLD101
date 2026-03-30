package com.example.BookMyShow;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Theater {
    private final int theaterId;
    private final String name;
    private final String city;
    private final Map<Integer, Auditorium> auditoriums = new HashMap<>();
    private final Set<Integer> movieIds = new HashSet<>();

    public Theater(int theaterId, String name, String city) {
        this.theaterId = theaterId;
        this.name = name;
        this.city = city;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Map<Integer, Auditorium> getAuditoriums() {
        return Collections.unmodifiableMap(auditoriums);
    }

    public Set<Integer> getMovieIds() {
        return Collections.unmodifiableSet(movieIds);
    }

    public boolean hasMovie(int movieId) {
        return movieIds.contains(movieId);
    }

    public void addAuditorium(Auditorium auditorium) {
        auditoriums.putIfAbsent(auditorium.getAuditoriumId(), auditorium);
    }

    public void registerShow(Show show, SeatMap seatMap) {
        movieIds.add(show.getMovieId());
        Auditorium auditorium = auditoriums.computeIfAbsent(show.getAuditoriumId(), Auditorium::new);
        auditorium.addSeatMap(show.getShowId(), seatMap);
        System.out.println("[Theater] Registered show " + show.getShowId() + " for theater " + theaterId);
    }

    public Optional<SeatMap> seatMapForShow(int showId) {
        return auditoriums.values().stream()
                .map(auditorium -> auditorium.getSeatMap(showId).orElse(null))
                .filter(map -> map != null)
                .findFirst();
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId=" + theaterId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
