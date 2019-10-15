package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLotsToManage) {
        super(parkingLotsToManage);
    }

    @Override
    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotList = getParkingLotsToManage();

        ParkingTicket ticket;
        ParkingLot parkingLot = parkingLotList.stream().
          reduce((a,b) -> a.getAvailableParkingPosition() > b.getAvailableParkingPosition() ? b : a).orElse(null);

         if ( parkingLot.getAvailableParkingPosition() == 0) {
                setLastErrorMessage("Not enough position.");
                ticket = null;
         } else {
                ticket = parkingLot.park(car);
         }

        return ticket;
    }
}
