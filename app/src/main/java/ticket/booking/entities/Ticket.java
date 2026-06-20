package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class Ticket {
    //Fields
    private String ticketId;
    private String userId;
    private Route route;
    private String dateOfTravel;
    private Train train;

    //Constructor
    public Ticket(){}

    public Ticket(String ticketId, String userId, Route route, String dateOfTravel, Train train){
        this.ticketId = ticketId;
        this.userId = userId;
        this.route = route;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, route.getSource(), route.getDestination(), dateOfTravel);
    }

    public String getTicketId(){
        return ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }
    
    public Route getRoute() {
    	return route; 
    }
    
    public void setRoute(Route route) {
    	this.route = route; 
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getDateOfTravel(){
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }

    public Train getTrain(){
        return train;
    }

    public void setTrain(Train train){
        this.train = train;
    }

}
