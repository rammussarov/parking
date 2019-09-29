package kg.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kg.parking.dto.ParkingPlaceDto;
import kg.parking.exceptions.ParkingPlaceNotFoundException;
import kg.parking.exceptions.ResourceNotFoundException;
import kg.parking.service.ParkingPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parking/v1/parkingPlace")
@Api(value = "Parking places management", description = "Operations on parking places")
public class ParkingController {

    @Autowired
    private ParkingPlaceService parkingPlaceService;

    @GetMapping("/freePlaces")
    @ApiOperation(value = "View a list of available parking places", response = List.class)
    public ResponseEntity<List<ParkingPlaceDto>> getAvailablePlaces() {
        List<ParkingPlaceDto> availablePlaces = parkingPlaceService.getAvailablePlaces().stream().map(ParkingPlaceDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(availablePlaces);
    }

    @PostMapping("/takePlace/{id}")
    @ApiOperation(value = "Marks that place is taken. Parking places use this route when car arrives on place.")
    public ResponseEntity<String> setParkingPlaceTaken(@ApiParam(value = "Parking place id that should marked as taken", required = true)
                                     @PathVariable(value = "id") Long parkingPlaceId) throws ResourceNotFoundException {
        try {
            parkingPlaceService.takePlace(parkingPlaceId);
            return ResponseEntity.ok("Ok");
        } catch (ParkingPlaceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/releasePlace/{id}")
    @ApiOperation(value = "Marks that place is free. Parking places use this route when car leaves the place.")
    public ResponseEntity<String> setParkingPlaceFree(@ApiParam(value = "Parking place id that should marked as free", required = true)
                                    @PathVariable(value = "id") Long parkingPlaceId) throws ResourceNotFoundException {
        try {
            parkingPlaceService.releasePlace(parkingPlaceId);
            return ResponseEntity.ok("Ok");
        } catch (ParkingPlaceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
