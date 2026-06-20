package ticket.booking.services;

import ticket.booking.entities.Route;
import ticket.booking.entities.SeatLayout;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    

    private TrainService trainService;
    
    private UserService userService;

    public UserBookingService() throws IOException{
        this.userService = new UserService();
        this.trainService = new TrainService();
    }

    

    public void fetchBookings(){
        System.out.println("Fetching your bookings");
        user.printTickets();
    }

    public void setUser(User user){
        this.user = user;
    }

    public boolean cancelBooking(String ticketId) throws IOException{

    	boolean isRemoved = user.cancelTicket(ticketId);
    	
        if(isRemoved) {
           
            userService.saveUserListToFile();
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return true;
        }else{
            System.out.println("No ticket found with ID " + ticketId);
            return false;
        }
    }

    public List<Train> getTrains (Route route) throws IOException {
        try{
            return trainService.searchTrains(route);
        }catch (Exception ex){
            System.out.println("There is something wrong!");
            
            return Collections.emptyList();
        }
    }

    public SeatLayout fetchSeats(Train train){
        return train.getSeats();
    }

    private void addTicketToUser(Ticket ticket) throws IOException {
        user.getTicketsBooked().add(ticket);
        userService.saveUserListToFile();
    }

    public boolean signUp(User user) throws IOException {
        return userService.signUp(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            
            if (!trainService.reserveSeat(train, row, seat)) {
                return false;
            }

            Ticket ticket = new Ticket(
                UserServiceUtil.generateTicketId(),
                user.getUserId(),
                new Route(train.getStartStation(), train.getEndStation()),
                LocalDate.now().toString(),
                train
            );

            addTicketToUser(ticket);

            System.out.println("Seat booked successfully!");
            System.out.println(ticket.getTicketInfo());
            return true;
 
        } catch (IOException ex){
            return false;
        }
    }

    

}
