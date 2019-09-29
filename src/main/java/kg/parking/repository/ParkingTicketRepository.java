package kg.parking.repository;

import kg.parking.entity.ParkingTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Long> {
}
