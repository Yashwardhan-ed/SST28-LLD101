package com.example.BookMyShow;

public class Seat {
	private final int seatId;
	private final SeatType seatType;
	private SeatStatus seatStatus;
	private final double basePrice;

	public Seat(int seatId, SeatType seatType, double basePrice) {
		this.seatId = seatId;
		this.seatType = seatType;
		this.basePrice = basePrice;
		this.seatStatus = SeatStatus.PENDING;
	}

	public int getSeatId() {
		return seatId;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public double getBasePrice() {
		return basePrice;
	}

	@Override
	public String toString() {
		return "Seat{" +
				"seatId=" + seatId +
				", seatType=" + seatType +
				", seatStatus=" + seatStatus +
				", basePrice=" + basePrice +
				'}';
	}
}
