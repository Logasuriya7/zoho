//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
public class TrainBooking {

    static Scanner sc = new Scanner(System.in);
    static List<Booking> bookings = new ArrayList<>();
    static int bookingid = 1;
    static int Upper = 1;
    static int Lower = 1;
    static int Middle = 1;
    static int rac = 1;
    static int waitinglist = 2;
    static int upperSeatNo = 1;
    static int lowerSeatNo = 1;
    static int middleSeatNo = 1;
    static Services services = new Services();



    public static void main(String[] args) {
        boolean flag = true;
        while(flag)
        {
            System.out.println("1. Booktrain");
            System.out.println("2. CheckStatus");
            System.out.println("3. CancelBooking");
            System.out.println("4. CheckStatus");
            System.out.println("5. SeatAvailability");
            System.out.println("6. Exit");

            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    services.Booktrain();
                    break;
                case 2:
                    services.Display();
                    break;
                case 3:
                    System.out.println("Enter the bookingid to cancel");
                    int cancelid = sc.nextInt();
                    Services.DeleteBooking(cancelid);
                    break;
                case 4:
                    System.out.println("Enter the bookingid");
                    int bookingid = sc.nextInt();
                    services.CheckStatus(bookingid);
                    break;
                case 5:
                    services.SeatAvailability();
                case 6:
                    flag =  false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }

    }

}
