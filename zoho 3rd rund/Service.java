public class Services extends TrainBooking {
    public static void Booktrain() {
        System.out.println("Enter your name: ");
        String name = sc.next();
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
        System.out.println("Enter your gender: ");
        String gender = sc.next();
        System.out.println("Enter your bearth preferance: ");
        String bearthPreferance = sc.next();
        String seat = Seatallocate(bearthPreferance, bookingid);
        if(seat.equals("No seats available"))
        {
            System.out.println("seat");
        }
        else {
            Booking booking = new Booking(bookingid, name, age, gender, seat);
            bookings.add(booking);
            System.out.println("Booking "+ bookingid + " is booked!");
            bookingid++;
        }
    }


    public static void Display() {
        if(bookings.isEmpty())
        {
            System.out.println("There is no booking to check!");
        }
        else {
            for(Booking booking : bookings)
            {
                System.out.println(booking.toString());
            }
        }
    }


//    public static void DeleteBooking(int cancelId) {
//        Booking cancelled = null;
//
//        // Step 1: find booking to cancel
//        for (Booking b : bookings) {
//            if (b.Bookingid == cancelId) {
//                cancelled = b;
//                break;
//            }
//        }
//
//        if (cancelled == null) {
//            System.out.println("Booking ID not found!");
//            return;
//        }
//
//        String freedSeat = cancelled.bearth;
//        bookings.remove(cancelled);
//        System.out.println("Booking " + cancelId + " cancelled. Freed seat: " + freedSeat);
//
//        // Step 2: If seat was confirmed (U/L/M), promote RAC
//        if (freedSeat.startsWith("U") || freedSeat.startsWith("M") || freedSeat.startsWith("L")) {
//            Booking racBooking = null;
//            for (Booking b : bookings) {
//                if (b.bearth.startsWith("R")) {  // find first RAC
//                    racBooking = b;
//                    break;
//                }
//            }
//
//            if (racBooking != null) {
//                String oldRacSeat = racBooking.bearth;
//                racBooking.bearth = freedSeat; // promote to confirmed
//                System.out.println("RAC passenger " + racBooking.Bookingid + " promoted to " + freedSeat);
//
//                // Step 3: Promote WL → RAC
//                Booking wlBooking = null;
//                for (Booking b : bookings) {
//                    if (b.bearth.startsWith("W")) { // find first Waiting
//                        wlBooking = b;
//                        break;
//                    }
//                }
//                if (wlBooking != null) {
//                    wlBooking.bearth = oldRacSeat; // WL now gets RAC seat
//                    System.out.println("WL passenger " + wlBooking.Bookingid + " promoted to " + oldRacSeat);
//                }
//            } else {
//                // No RAC, so just free the seat count
//                if (freedSeat.startsWith("U")) Upper++;
//                else if (freedSeat.startsWith("M")) Middle++;
//                else if (freedSeat.startsWith("L")) Lower++;
//            }
//        }
//    }
    public static void DeleteBooking(int Cancelid) {

        Booking cancel = null;
        for (Booking booking : bookings) {
            if (booking.Bookingid == Cancelid) {
                cancel = booking;
                break;
            }
        }
        if (cancel == null) {
            System.out.println("There is no booking to check!");
            return;
        }
        String cancledbreath = cancel.bearth;
        bookings.remove(cancel);
        System.out.println("Bearth "+ cancledbreath+ " is free");
        Booking racbearth = null;
        String rac = null;
        if(cancledbreath.startsWith("U") || cancledbreath.startsWith("M") || cancledbreath.startsWith("L"))
        {
            for (Booking booking : bookings) {
                if (booking.bearth.startsWith("R")) {
                    rac = booking.bearth;
                    racbearth = booking;
                    break;
                }
            }
        }

        Booking wlbooking = null;
        if(racbearth != null)
        {
            racbearth.bearth = cancledbreath;
            for (Booking booking : bookings) {
                if (booking.bearth.startsWith("W")) {
                    booking.bearth = rac;
                }
            }
        }
        else {
            if(cancledbreath.startsWith("U")){Upper++;}
            if(cancledbreath.startsWith("M")){Middle++;}
            if(cancledbreath.startsWith("L")){Lower++;}
        }



    }

    public static void CheckStatus(int bookingid) {
        for(Booking booking : bookings) {
            if (booking.Bookingid == bookingid) {
                System.out.println("Your current status:  "+ booking.bearth);
            }
        }
    }


    public static String Seatallocate(String bearthpreferance, int bookingid) {
        if (bearthpreferance.equals("upper") && Upper > 0)
        {
            Upper--;
            return "U" + upperSeatNo++;
        }
        else if(bearthpreferance.equals("lower") && Lower > 0)
        {
            Lower--;
            return "L" + lowerSeatNo++;
        }
        else if(bearthpreferance.equals("middle") && Middle > 0) {
            Middle--;
            return "M"  + middleSeatNo++;
        }
        else if(Upper > 0)
        {
            Upper--;
            return "U"  + upperSeatNo++;
        }
        else if(Middle > 0)
        {
            Middle--;
            return "M" + middleSeatNo++;
        }
        else if(Lower > 0)
        {
            Lower--;
            return "L" +  lowerSeatNo++;
        }
        else if(rac > 0)
        {
            rac--;
            return "R" + rac;
        }
        else if(waitinglist > 0)
        {
            waitinglist--;
            return "Waiting" +  waitinglist;
        }
        return "No seats available";

    }

    public void SeatAvailability() {
        System.out.println("*****************Seat Availability*****************");

        System.out.println("Upper: " + Upper);
        System.out.println("Lower: " + Lower);
        System.out.println("Middle: " + Middle);
    }
}
