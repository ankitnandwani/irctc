package ticket.booking;

import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
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
                    try {
                        userBookingService.logIn(scanner);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    userBookingService.fetchBookings(scanner);
                    break;
                case 4:
                    userBookingService.searchTrains(scanner);
                    break;
                case 5:
                    userBookingService.bookSeat(scanner);
                    break;
                case 6:
                    userBookingService.cancelBooking(scanner);
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