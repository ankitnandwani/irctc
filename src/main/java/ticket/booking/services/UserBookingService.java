package ticket.booking.services;

import com.google.gson.Gson;
import ticket.booking.entities.User;

import java.io.File;
import java.util.List;

public class UserBookingService {

    private static final String USERS_PATH="../localDb/users.json";

    private Gson gson = new Gson();
    private User user;
    private List<User> userList;
    public UserBookingService(User user){
        this.user=user;
        File users = new File(USERS_PATH);
    }
}
