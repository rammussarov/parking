package kg.parking.service.impl;

import kg.parking.entity.ParkingTicket;
import kg.parking.exceptions.ParkingTicketNotFoundException;
import kg.parking.repository.ParkingTicketRepository;
import kg.parking.service.ParkingTicketService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log
public class ParkingTicketServiceImpl implements ParkingTicketService {

    private final ParkingTicketRepository repository;

    @Autowired
    public ParkingTicketServiceImpl(ParkingTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParkingTicket save(ParkingTicket parkingTicket) {
        return repository.save(parkingTicket);
    }

    @Override
    public ParkingTicket getParkingPrice(Long parkingTicketId) throws ParkingTicketNotFoundException {
        Optional<ParkingTicket> parkingTicketOptional = repository.findById(parkingTicketId);
        if (!parkingTicketOptional.isPresent()) {
            log.info("Couldn't find parking price with id = " + parkingTicketId);
            throw new ParkingTicketNotFoundException(String.format("Парковочный талон с id = %s не найден.", parkingTicketId));
        }

        ParkingTicket parkingTicket = parkingTicketOptional.get();
        parkingTicket.setEndDate(LocalDateTime.now());
        parkingTicket.setPrice(countTicketPrice(parkingTicket));
        if (parkingTicket.getParkingPlace().getIsBooked()) {
            parkingTicket.getParkingPlace().setIsBooked(false);
        }

        return repository.save(parkingTicket);
    }

    private Long countTicketPrice(ParkingTicket parkingTicket) {
        long minsOfParking = Duration.between(parkingTicket.getStartDate(), parkingTicket.getEndDate()).toMinutes();
        double price = ((double) minsOfParking / 60) * parkingTicket.getParkingPlace().getParkingFee();
        return (long) Math.ceil( price);
    }
}
