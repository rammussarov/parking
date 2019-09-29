package kg.parking.repository;

import kg.parking.entity.ParkingPlace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParkingPlaceRepository extends CrudRepository<ParkingPlace, Long> {
    List<ParkingPlace> findAllByIsBookedFalseAndIsTakenFalse();
}
