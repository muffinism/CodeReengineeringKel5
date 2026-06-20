package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> userList;
    private final ObjectMapper objectMapper;
    private final String USERS_PATH = "src/main/java/ticket/booking/localDb/users.json";

    public UserService() throws IOException{
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadUsers();
    }

    private void loadUsers() throws IOException{
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }

    public void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public boolean signUp(User user) throws IOException{
        try{
            Optional<User> foundUser = userList.stream().filter(user1 -> {
                return user1.getUsername().equals(user.getUsername());
            }).findFirst();

            if (foundUser.isPresent()) {
                
                System.out.println("Username already taken!");
                return false;
            }

            userList.add(user);
            saveUserListToFile();
        }catch (Exception ex){
            System.out.println("saving user list to file failed " + ex.getMessage());
            return false;
        }
        return true;
    }

    public Optional<User> getUserByUsername(String username){
        return userList.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

}
