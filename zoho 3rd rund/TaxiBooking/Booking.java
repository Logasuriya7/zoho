import java.util.*;
public class Booking {
    int bookingid, customerid, pickupTime, dropTime, amount;
    char pickuplocation, droplocation;

    public Booking(int bookingid, int customerid,
                   char pickuplocation, char droplocation,
                   int pickupTime, int dropTime, int amount) {
        this.bookingid = bookingid;
        this.customerid = customerid;
        this.pickuplocation = pickuplocation;
        this.droplocation = droplocation;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }
}
