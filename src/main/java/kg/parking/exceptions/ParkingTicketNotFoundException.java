package kg.parking.exceptions;

public class ParkingTicketNotFoundException extends Exception {

    public ParkingTicketNotFoundException(String message) {
        super(message);
    }

    public ParkingTicketNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
