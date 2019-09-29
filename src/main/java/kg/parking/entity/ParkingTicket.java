package kg.parking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_ticket")
@Data
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_ticket_seq")
    @SequenceGenerator(name = "parking_ticket_seq", sequenceName = "parking_ticket_seq", allocationSize = 100)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parking_place_id")
    private ParkingPlace parkingPlace;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price")
    private Long price;

    public ParkingTicket() {
    }

    public ParkingTicket(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
        this.startDate = LocalDateTime.now();
    }
}
