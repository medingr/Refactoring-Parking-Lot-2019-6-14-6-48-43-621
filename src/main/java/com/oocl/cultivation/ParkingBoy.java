package com.oocl.cultivation;
import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    static final String NOT_ENOUGH_POSITION = "Not enough position.";
    private static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket";
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

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

        if(ticket == null) {
            lastErrorMessage = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
            return null;
        }
        Car car  = parkingLotsToManage.stream().map( lot -> lot.fetch(ticket)).filter(Objects::nonNull).findFirst().orElse(null);
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
