package kg.parking.service;

import kg.parking.entity.ParkingPlace;
import kg.parking.entity.ParkingTicket;
import kg.parking.exceptions.ParkingPlaceNotFoundException;

import java.util.List;

public interface ParkingPlaceService {

    List<ParkingPlace> getAvailablePlaces();
    ParkingTicket bookPlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException;
    void takePlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException;
    void releasePlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException;
}
