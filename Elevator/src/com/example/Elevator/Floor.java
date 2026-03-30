package com.example.Elevator;

public class Floor {
	private final int floorNumber;
	private final OutsidePanel outsidePanel;

	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.outsidePanel = new OutsidePanel();
		System.out.println("Created floor: " + floorNumber);
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public OutsidePanel getOutsidePanel() {
		return outsidePanel;
	}
}
