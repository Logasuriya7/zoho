import java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
        static List<Taxi> taxies = new ArrayList<>();
        static Scanner sc = new Scanner(System.in);
        static int coustomerCount = 1;



    public static void main(String[] args) {
        System.out.println("Enter the number of taxies");
        int taxis = sc.nextInt();
        for(int i=1; i<=taxis; i++)
        {
            Taxi taxi = new Taxi(i);
            taxies.add(taxi);
        }

        boolean flag = true;

        while(flag)
        {
            System.out.println("\n1. Book Taxi\n2. Show Details\n3. Exit");
            System.out.println("Enter your chois:  ");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    bookTaxi();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    System.out.println("Bye!");
                    flag = false;
            }

        }

    }

    public static void bookTaxi() {
        int customerId = coustomerCount++;
        System.out.println("Enter pickup location(A-F): ");
        char pickuplocation = sc.next().toUpperCase().charAt(0);
        System.out.println("Enter droup location(A-F): ");
        char droplocation = sc.next().toUpperCase().charAt(0);
        System.out.println("Enter pickup Time(hours): ");
        int pickuptime = sc.nextInt();

        Taxi selectedTaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for(Taxi taxi : taxies)
        {
            if(taxi.isAvailable(pickuptime))
            {
                int distance = Math.abs(taxi.currentpoint - pickuplocation);
                if(distance < minDistance )
                {
                    minDistance = distance;
                    selectedTaxi = taxi;
                }
                if(distance == minDistance && taxi.TotalEarning < selectedTaxi.TotalEarning)
                {
                    minDistance = distance;
                    selectedTaxi = taxi;
                }
            }

        }
        if(selectedTaxi == null)
        {
            System.out.println("Booking rejected. No taxi available. ");
            return;
        }
        int travel =  Math.abs(droplocation - pickuplocation);
        int droptime =  pickuptime + travel;
        int amount = selectedTaxi.CalculateEarning(pickuplocation, droplocation);
        int bookingid = selectedTaxi.bookings.size() + 1;

        Booking booking = new Booking(bookingid, customerId, pickuplocation, droplocation, pickuptime, droptime, amount);
        selectedTaxi.addbooking(booking);
        System.out.println("Taxi - " + selectedTaxi.id +" is allocated.");
    }
    public static void display()
    {
        for(Taxi taxi : taxies){
            System.out.println("Taxi - "+ taxi.id +" Total Earning: "+ taxi.TotalEarning);
            System.out.printf("%-10s %-10s %-5s %-5s %-12s %-9s %-6s%n",
                    "BookingId", "Customerid", "From", "To", "PickupTime", "DropTime", "Amount");
            for(Booking booking : taxi.bookings){
                System.out.printf("%-10d %-10d %-5c %-5c %-12d %-9d %-6d%n",
                        booking.bookingid, booking.customerid,
                        booking.pickuplocation, booking.droplocation,
                        booking.pickupTime, booking.dropTime, booking.amount);
            }
        }
    }

}