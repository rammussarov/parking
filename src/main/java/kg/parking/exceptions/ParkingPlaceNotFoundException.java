package kg.parking.exceptions;

public class ParkingPlaceNotFoundException extends Exception {

    public ParkingPlaceNotFoundException(String message) {
        super(message);
    }

    public ParkingPlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
