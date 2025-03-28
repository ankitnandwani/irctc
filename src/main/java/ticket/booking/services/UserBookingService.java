package ticket.booking.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private static final String USERS_PATH="src/main/java/localDb/users.json";

    private Gson gson = new Gson();
    private User user;
    private List<User> userList;
    public UserBookingService(User user){
        this.user=user;
        File users = new File(USERS_PATH);
        try (FileReader reader = new FileReader(users)){
            Type userListType = new TypeToken<List<User>>() {}.getType();
            userList = gson.fromJson(reader, userListType);
        } catch (IOException e){
            throw new RuntimeException("Failed to load users from json", e);
        }

    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

//    public Boolean signUp(User user1){
//        try {
//            userList.add(user1);
//            saveUserListToFile();
//            return Boolean.TRUE;
//        } catch (IOException ex){
//            return Boolean.FALSE;
//        }
//    }
}
