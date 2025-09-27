import java.util.*;

public class Taxi {
    int id;
    char currentpoint = 'A' ;
    int TotalEarning = 0;
    Boolean IsAvailable = true;
    List<Booking> bookings = new ArrayList();


    public Taxi(int id) {
        this.id = id;
    }


    public Boolean isAvailable(int RequestTime )
    {
        if(bookings.isEmpty()){
            return true;
        }
        Booking lastbooking = bookings.get(bookings.size() -1);
        return lastbooking.dropTime <= RequestTime;
    }

    public int CalculateEarning(char pickup, char drop)
    {
        int distance = Math.abs(pickup - drop) * 15;
        return 100 + Math.max(0,(distance-5) * 10);
    }

    public void addbooking(Booking booking)
    {
        bookings.add(booking);
        currentpoint = booking.droplocation;
        TotalEarning += booking.amount;
    }

    // isavailabe?, Calculatearning?, addBooking.


}
