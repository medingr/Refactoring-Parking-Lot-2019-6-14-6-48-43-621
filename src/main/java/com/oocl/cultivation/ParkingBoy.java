package com.oocl.cultivation;
import java.util.List;

import static java.util.Objects.isNull;

public class ParkingBoy {
    //private
    public static final String NOT_ENOUGH_POSITION = "Not enough position.";
    public static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    public List<ParkingLot> getParkingLotsToManage() {
        return parkingLotsToManage;
    }

    private final List<ParkingLot> parkingLotsToManage;

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    private String lastErrorMessage;

    public ParkingBoy(List <ParkingLot> parkingLotsToManage) {
        this.parkingLotsToManage = parkingLotsToManage;
    }

    public ParkingTicket park(Car car) {

            ParkingLot parkingLot  = parkingLotsToManage
                    .stream()
                    .filter(lot -> lot.getAvailableParkingPosition() != 0)
                    .findFirst()
                    .orElse(null);

            if (parkingLot == null) {
                lastErrorMessage = NOT_ENOUGH_POSITION;
                 return null;
            }
            return  parkingLot.park(car);
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = new Car();
            //method for checking then condition
        if(ticket == null) {
            lastErrorMessage = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            return null;
        }
        //filter parking lot with ticket of car then fetch
        for(ParkingLot parkingLot : parkingLotsToManage){
            car = parkingLot.fetch(ticket);

            if(car != null) {
                return car;
            }else{ //unnecessary
                car = null;
            }
        }

        if(car == null){
            lastErrorMessage = UNRECOGNIZED_PARKING_TICKET;
            return null;
        }

        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
