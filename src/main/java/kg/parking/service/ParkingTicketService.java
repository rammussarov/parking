package kg.parking.service;

import kg.parking.entity.ParkingTicket;
import kg.parking.exceptions.ParkingTicketNotFoundException;

public interface ParkingTicketService {

    ParkingTicket save(ParkingTicket parkingTicket);
    ParkingTicket getParkingPrice(Long parkingTicketId) throws ParkingTicketNotFoundException;
}
