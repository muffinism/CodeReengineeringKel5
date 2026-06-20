package ticket.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class User {

    private String username;
    private String userId;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;

   
    public User(){}

    public User(String username, String hashedPassword, List<Ticket> ticketsBooked, String userId){
        this.username = username;
        this.userId = userId;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked != null ? ticketsBooked : Collections.emptyList();
    }

    
    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void printTickets(){

        if(ticketsBooked.isEmpty()){
            System.out.println("No tickets booked yet!");
            return;
        }else {
            for (Ticket ticket : ticketsBooked) {
                System.out.println(ticket.getTicketInfo());
            }
        }
    }

    public String getUserId() {
        return userId;
    }


    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    public boolean cancelTicket(String ticketId){
        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }
        return this.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId) );
        
    }
}

