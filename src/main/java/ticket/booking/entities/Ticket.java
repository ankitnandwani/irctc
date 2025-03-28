package ticket.booking.entities;

import lombok.Getter;

@Getter
public class Ticket {
    private String ticketId;
    private String userId;
    private String origin;
    private String destination;
    private String dateOfTravel;
    private Train train;

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to user %s from %s to %s on %s", ticketId, userId, origin, destination, dateOfTravel);
    }
}
