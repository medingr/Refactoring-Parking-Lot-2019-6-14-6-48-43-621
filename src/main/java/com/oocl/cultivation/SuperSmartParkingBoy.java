package com.oocl.cultivation;

import java.util.List;

import static java.util.Objects.isNull;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotsToManage) {
        super(parkingLotsToManage);
    }

    @Override
    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotList = getParkingLotsToManage();

        ParkingLot parkingLot = parkingLotList.stream()
                .filter(parkingLotInList -> parkingLotInList.getAvailableParkingPosition() != 0)
                .reduce((a,b) -> ( a.getAvailableParkingPosition() / a.getCapacity() ) >
                        (b.getAvailableParkingPosition() / b.getCapacity())  ? b : a).orElse(null);

        if (isNull(parkingLot)) {
            setLastErrorMessage(NOT_ENOUGH_POSITION);
            return null;
        }
        return parkingLot.park(car);
    }
}
