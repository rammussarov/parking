package kg.parking.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "parking_place")
@Data
public class ParkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_place_seq")
    @SequenceGenerator(name = "parking_place_seq", sequenceName = "parking_place_seq", allocationSize = 100)
    private Long id;

    @Column(name = "position")
    private String position;

    @Column(name = "is_taken")
    private Boolean isTaken;

    @Column(name = "is_booked")
    private Boolean isBooked;

    @Column(name = "parking_fee")
    private Long parkingFee;

}
