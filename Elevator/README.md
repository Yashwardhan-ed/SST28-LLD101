# Elevator Design (LLD)

A minimal elevator control simulation demonstrating panels, buttons, move strategies, and sensor decorators for multiple cars.

![Design diagram](src/Elevator%20Design.drawio.png)

## Components
- **ElevatorSystem / ElevatorManager**: bootstraps elevators and floors, delegates inside/outside calls, and picks a car through a pluggable `IElevatorMoveStrategy`.
- **Elevator**: tracks id, weight limit, current floor/status, inside panel, and a request queue; executes `move()` to service the next queued stop.
- **Move strategies**: `FCFS` grabs the first available car; `EvenOdd` prefers even/odd car ids for even/odd floors then falls back.
- **Panels & buttons**: `OutsidePanel` exposes up/down `ElevatorRequestButton`s; `InsidePanel` hosts `ElevatorFloorButton`s plus door/alarm buttons. `Panel`/`ElevatorButton` give a simple base for adding controls.
- **Sensors (decorator)**: `WeightSensor` and `DoorSensor` can be wrapped by `WeightAlertSensor`, `CurrentFloorSensor`, and `DoorLoggingSensor` to add cross-cutting behavior.
- **Emergency & operator hooks**: `EmergencyStrategy` shows where emergency flows plug in; `Operator` can change elevator status.

## Walkthrough
1. A passenger presses an up/down button on a floor (`OutsidePanel` → `ElevatorRequestButton`).
2. `ElevatorManager` gathers elevator snapshots and asks the configured strategy to select a car.
3. The chosen `Elevator` enqueues the target floor and runs `move()` to update status and current floor.
4. Riders inside use `InsidePanel` floor buttons (or door/alarm controls), which enqueue additional stops.
5. Sensors on the car can be read to log door events or check weight thresholds; decorators compose extra checks without changing base sensors.
6. Operators or emergencies can override status via `Operator`/`EmergencyStrategy`.

## Demo run (Application.java)
- Builds floors 0–5, two elevators (ids 1 and 2) with an `FCFS` strategy, and decorates sensors on elevator 1.
- Issues sample outside/inside requests, checks weight, reads sensors, and triggers an emergency hook.
- To run: compile the `src` tree and execute `com.example.Elevator.Application` (e.g., with `javac` then `java` on the classpath containing `src`).

## Extending
- Add more selection logic by implementing `IElevatorMoveStrategy`.
- Enrich `Elevator` movement (doors, travel time) or replace the simple queueing logic.
- Introduce real sensor data by swapping the placeholder `readSensor()` implementations.
