package kg.parking.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kg.parking.entity.ParkingPlace;
import lombok.Getter;

@Getter
@ApiModel(description = "Parking place details")
public class ParkingPlaceDto {
    @ApiModelProperty(notes = "The database generated parking place ID")
    private Long id;

    @ApiModelProperty(notes = "Parking place position")
    private String position;

    @ApiModelProperty(notes = "Parking fee per hour")
    private Long parkingFee;

    public ParkingPlaceDto(ParkingPlace parkingPlace) {
        id = parkingPlace.getId();
        position = parkingPlace.getPosition();
        parkingFee = parkingPlace.getParkingFee();
    }
}
