package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    List<ParkingLot> parkingLotsToManage = new ArrayList<>();

    @Test
    void should_parking_boy_park_car_into_parking_lot() {

        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);
        Car car = new Car();
        ParkingTicket ticket = new ParkingTicket();

        parkBoy.park(car);
        assertNotNull(ticket);
    }

    @Test
    void should_fetch_car_by_parking_ticket_from_parking_lot() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

        Car car = new Car();
        ParkingTicket ticket = new ParkingTicket();

        parkBoy.park(car);
        parkBoy.fetch(ticket);
        assertNotNull(car);
    }

    @Test
    public void should_park_multiple_cars_into_lot_and_fetch_ticket() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

        Car car = new Car();
        Car car2 = new Car();

        ParkingTicket ticket = parkBoy.park(car);
        ParkingTicket ticket2 = parkBoy.park(car2);

        assertNotNull(ticket);
        assertNotNull(ticket2);
        assertNotEquals(ticket,ticket2);
    }

    @Test
    public void should_customer_give_wrong_ticket_no_car_to_fetch() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);
        Car car = new Car();
        Car carFetchedByTicket = parkBoy.fetch(null);

        assertNull(carFetchedByTicket);
    }

    @Test
    public void should_not_fetch_when_ticket_already_used() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);
        Car car = new Car();
        ParkingTicket ticket = parkBoy.park(car);
        Car carFetchedByTicket = parkBoy.fetch(ticket);
        carFetchedByTicket = parkBoy.fetch(ticket);

        assertNull(carFetchedByTicket);
    }

    @Test
    public void should_not_park_car_when_parking_lot_is_full() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

        Car car = new Car();
        ParkingTicket ticket = new ParkingTicket();
        for ( int i = 0 ; i <= 10 ; i++ ){
             ticket = parkBoy.park(car);
        }
        assertNull(ticket);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

        Car car = new Car();
        ParkingTicket ticket = parkBoy.park(car);

        Car car2 = parkBoy.fetch(new ParkingTicket());
        assertNull(car2);
        assertEquals(parkBoy.getLastErrorMessage(),"Unrecognized parking ticket.");
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_no_ticket_from_customer() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

        Car car = new Car();
        ParkingTicket ticket = parkBoy.park(car);
        car = parkBoy.fetch(null);

        assertNull(car);
        assertEquals(parkBoy.getLastErrorMessage(),"Please provide your parking ticket");
    }

    @Test
    public void should_return_not_enough_position_when_parkBoy_park_without_position() {
        ParkingLot parkLot = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);


        ParkingTicket ticket = new ParkingTicket();
        for ( int i = 0 ; i <= 10 ; i++ ){
            Car car = new Car();
            ticket = parkBoy.park(car);
        }

        assertNull(ticket);
        assertEquals(parkBoy.getLastErrorMessage(),"Not enough position.");
    }

    @Test
    public void should_park_boy_park_into_multiple_parking_lots() {

            ParkingLot parkLot = new ParkingLot();
            ParkingLot parkLotTwo = new ParkingLot();
            parkingLotsToManage.add(parkLot);
            parkingLotsToManage.add(parkLotTwo);
            ParkingBoy parkBoy = new ParkingBoy(parkingLotsToManage);

            for ( int i = 0 ; i <= 10 ; i++ ){
                Car car = new Car();
                parkBoy.park(car);
            }

            ParkingTicket ticket =  parkBoy.park(new Car());
            assertNotNull(ticket);
    }

    @Test
    public void should_park_in_parking_lot_with_more_empty_position_by_smart_parking_boy() {
        ParkingLot parkLot = new ParkingLot();
        ParkingLot parkLotTwo = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        parkingLotsToManage.add(parkLotTwo);
        SmartParkingBoy smartBoy = new SmartParkingBoy(parkingLotsToManage);
         Car car = new Car();
         smartBoy.park(car);
         smartBoy.park(car);


        ParkingTicket ticket =  smartBoy.park(new Car());
        assertNotNull(ticket);
    }

    @Test
    public void should_park_in_parking_lot_with_larger_availabe_position_rate_by_super_smart_parking_boy() {
        ParkingLot parkLot = new ParkingLot();
        ParkingLot parkLotTwo = new ParkingLot();
        parkingLotsToManage.add(parkLot);
        parkingLotsToManage.add(parkLotTwo);
        SuperSmartParkingBoy superSmartBoy = new SuperSmartParkingBoy(parkingLotsToManage);
        Car car = new Car();
        superSmartBoy.park(car);
        superSmartBoy.park(car);


        ParkingTicket ticket =  superSmartBoy.park(car);
        car = superSmartBoy.fetch(ticket);
        assertNotNull(ticket);
        assertNotNull(car);

    }

}
