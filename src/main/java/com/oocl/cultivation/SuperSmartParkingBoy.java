package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotsToManage) {
        super(parkingLotsToManage);
    }

    @Override
    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotList = getParkingLotsToManage();

        ParkingTicket ticket;
        ParkingLot parkingLot = parkingLotList.stream().
                reduce((a,b) -> ( a.getAvailableParkingPosition() / a.getCapacity() ) >
                        (b.getAvailableParkingPosition() / b.getCapacity())  ? b : a).orElse(null);

        if ( parkingLot.getAvailableParkingPosition() == 0) {
            setLastErrorMessage(NOT_ENOUGH_POSITION);
            ticket = null;
        } else {
            ticket = parkingLot.park(car);
        }

        return ticket;
    }


}
