package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running train booking system");
        Scanner scanner = new Scanner(System.in);
        int option=0;
        UserBookingService userBookingService = new UserBookingService();
        while (option!=7){
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter username to signup: ");
                    String nameToSignup = scanner.next();
                    System.out.println("Enter password: ");
                    String passwordToSignup = scanner.next();
                    User userToSignup = User.builder().name(nameToSignup).password(passwordToSignup).hashPassword(UserServiceUtil.hashPassword(passwordToSignup)).userId(UUID.randomUUID().toString()).build();
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter username to login: ");
                    String usernameToLogin = scanner.next();
                    System.out.println("Enter password: ");
                    String passwordToLogin = scanner.next();
                    User userToLogin = User.builder().name(usernameToLogin).password(passwordToLogin).hashPassword(UserServiceUtil.hashPassword(passwordToLogin)).build();
                    userBookingService = new UserBookingService(userToLogin);
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Type your origin");
                    String origin = scanner.next();
                    System.out.println("Type your destination");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(origin, dest);
                    int index=1;
                    for (Train train: trains){
                        System.out.println(index+". Train id : "+train.getTrainId());
                        for (Map.Entry<String, String> entry: train.getStationTimes().entrySet()){
                            System.out.println("Station: "+entry.getKey()+" | Time: "+entry.getValue());
                        }
                        index++;
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    Train trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }


        }
    }


}