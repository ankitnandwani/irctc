package ticket.booking.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private static final String USERS_PATH="src/main/java/localDb/users.json";

    private Gson gson = new Gson();
    private User user;
    private List<User> userList;
    public UserBookingService(User user) {
        this.user=user;
        loadUsers();
    }

    public UserBookingService() {
        loadUsers();
    }

    public List<User> loadUsers() {
        File users = new File(USERS_PATH);
        try (FileReader reader = new FileReader(users)){
            Type userListType = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e){
            throw new RuntimeException("Failed to load users from json", e);
        }
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword())).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        try (FileWriter writer = new FileWriter(usersFile)){
            gson.toJson(userList, writer);
        }
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) throws IOException {
        List<Ticket> tickets = user.getTicketsBooked();
        tickets.remove(user.getTicketsBooked().stream().filter(e->e.getTicketId().equals(ticketId)).findFirst().get());
        saveUserListToFile();
        return true;
    }

    public List<Train> getTrains(String origin, String dest) {
        TrainService trainService = new TrainService();
        return trainService.searchTrains(origin, dest);
    }
}
