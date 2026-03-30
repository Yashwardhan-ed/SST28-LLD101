package com.example.Elevator;

import java.util.List;
import java.util.ArrayList;

public class FloorPanel extends Panel {
    private final List<ElevatorCallButton> callButtons;

    public FloorPanel(int floorNumber) {
        super(floorNumber);
        callButtons = new ArrayList<>();
        callButtons.add(new ElevatorCallButton(floorNumber, Direction.UP));
        callButtons.add(new ElevatorCallButton(floorNumber, Direction.DOWN));
        System.out.println("FloorPanel initialized for floor " + floorNumber);
    }
    
}
