package com.oocl.cultivation;
import java.util.List;

public class ParkingBoy {

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

            ParkingTicket ticket = new ParkingTicket();

            for (ParkingLot parkingLot : parkingLotsToManage)
            {
                if ( parkingLot.getAvailableParkingPosition() == 0) {
                    lastErrorMessage = NOT_ENOUGH_POSITION;
                    ticket = null;
                } else {
                    ticket = parkingLot.park(car);
                    break;

                }

            }
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car = new Car();

        if(ticket == null) {
            lastErrorMessage = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            return null;
        }

        for(ParkingLot parkingLot : parkingLotsToManage){
            car = parkingLot.fetch(ticket);

            if(car != null) {
                return car;
            }else{
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
