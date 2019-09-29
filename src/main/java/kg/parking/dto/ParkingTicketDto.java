package kg.parking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kg.parking.entity.ParkingTicket;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(description = "Parking ticket details")
public class ParkingTicketDto {
    @ApiModelProperty(notes = "The database generated parking ticket ID")
    private Long id;

    @ApiModelProperty(notes = "Parking place for this ticket")
    private String parkingPlace;

    @ApiModelProperty(notes = "Parking place fee")
    private Long parkingFee;

    @ApiModelProperty(notes = "Time of arriving on parking")
    private LocalDateTime startDate;

    @ApiModelProperty(notes = "Time of leaving parking")
    private LocalDateTime endDate;

    @ApiModelProperty(notes = "Total price of parking")
    private Long parkingPrice;

    public ParkingTicketDto(ParkingTicket parkingTicket) {
        id = parkingTicket.getId();
        parkingPlace = parkingTicket.getParkingPlace().getPosition();
        parkingFee = parkingTicket.getParkingPlace().getParkingFee();
        startDate = parkingTicket.getStartDate();
        endDate = parkingTicket.getEndDate();
        parkingPrice = parkingTicket.getPrice();
    }
}
