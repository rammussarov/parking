package kg.parking.service.impl;

import kg.parking.entity.ParkingPlace;
import kg.parking.entity.ParkingTicket;
import kg.parking.exceptions.ParkingPlaceNotFoundException;
import kg.parking.repository.ParkingPlaceRepository;
import kg.parking.service.ParkingPlaceService;
import kg.parking.service.ParkingTicketService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class ParkingPlaceServiceImpl implements ParkingPlaceService {

    private final ParkingPlaceRepository repository;
    private final ParkingTicketService parkingTicketService;

    @Autowired
    public ParkingPlaceServiceImpl(ParkingPlaceRepository repository, ParkingTicketService parkingTicketService) {
        this.repository = repository;
        this.parkingTicketService = parkingTicketService;
    }

    @Override
    public List<ParkingPlace> getAvailablePlaces() {
        return repository.findAllByIsBookedFalseAndIsTakenFalse();
    }

    @Override
    public ParkingTicket bookPlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException {
        ParkingPlace parkingPlace = getParkingPlace(parkingPlaceId);
        parkingPlace.setIsBooked(true);
        repository.save(parkingPlace);

        ParkingTicket parkingTicket = new ParkingTicket(parkingPlace);
        return parkingTicketService.save(parkingTicket);
    }

    @Override
    public void takePlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException {
        ParkingPlace parkingPlace = getParkingPlace(parkingPlaceId);
        parkingPlace.setIsBooked(false);
        parkingPlace.setIsTaken(true);
        repository.save(parkingPlace);
    }

    @Override
    public void releasePlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException {
        ParkingPlace parkingPlace = getParkingPlace(parkingPlaceId);
        parkingPlace.setIsTaken(false);
        repository.save(parkingPlace);
    }

    private ParkingPlace getParkingPlace(Long parkingPlaceId) throws ParkingPlaceNotFoundException {
        Optional<ParkingPlace> parkingPlaceOptional = repository.findById(parkingPlaceId);
        if (!parkingPlaceOptional.isPresent()) {
            log.info("Couldn't find parking place with id = " + parkingPlaceId);
            throw new ParkingPlaceNotFoundException(String.format("Парковочное место с id = %s не найдено!", parkingPlaceId));
        }

        return parkingPlaceOptional.get();
    }
}
