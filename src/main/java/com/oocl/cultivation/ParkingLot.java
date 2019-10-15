package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public int getCapacity() {
        return capacity;
    }

    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        if(getAvailableParkingPosition() == 0 ){
            return null;
        }
        cars.put(ticket,car);

        return ticket;
    }
    public Car fetch(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }
}
