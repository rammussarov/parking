package kg.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kg.parking.dto.ParkingTicketDto;
import kg.parking.entity.ParkingTicket;
import kg.parking.exceptions.ParkingPlaceNotFoundException;
import kg.parking.exceptions.ParkingTicketNotFoundException;
import kg.parking.exceptions.ResourceNotFoundException;
import kg.parking.service.ParkingPlaceService;
import kg.parking.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking/v1/parkingTicket")
@Api(value = "Parking tickets management", description = "Operations on parking tickets")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @Autowired
    private ParkingPlaceService parkingPlaceService;

    @PostMapping("/bookPlace/{id}")
    @ApiOperation(value = "Marks parking place as booked, so other cars can't take it. Entrance barrier should use this route when allowing car to enter to parking.")
    public ResponseEntity<ParkingTicketDto> bookParkingPlace(@ApiParam(value = "Parking place id that is booked", required = true)
                                             @PathVariable(value = "id") Long parkingPlaceId) throws ResourceNotFoundException {
        try {
            ParkingTicket parkingTicket = parkingPlaceService.bookPlace(parkingPlaceId);
            ParkingTicketDto result = new ParkingTicketDto(parkingTicket);
            return ResponseEntity.ok(result);
        } catch (ParkingPlaceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/getPrice/{id}")
    @ApiOperation(value = "Counts the price of parking. Exit barrier should use this route to show car drivers parking check.")
    public ResponseEntity<ParkingTicketDto> getResultParkingTicket(@ApiParam(value = "Parking ticket id that driver got on entrance", required = true)
                                                   @PathVariable(value = "id") Long parkingTicketId) throws ResourceNotFoundException {
        try {
            ParkingTicket parkingPrice = parkingTicketService.getParkingPrice(parkingTicketId);
            ParkingTicketDto result = new ParkingTicketDto(parkingPrice);
            return ResponseEntity.ok(result);
        } catch (ParkingTicketNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }
}
