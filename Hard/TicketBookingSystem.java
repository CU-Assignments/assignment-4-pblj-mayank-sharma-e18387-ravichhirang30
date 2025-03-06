import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public synchronized boolean bookTicket(String name, int seats) {
        if (seats > availableSeats) {
            System.out.println(name + " - Booking failed. Not enough seats available.");
            return false;
        }
        availableSeats -= seats;
        System.out.println(name + " - Booking successful! Seats booked: " + seats);
        return true;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String customerName;
    private int seats;

    public BookingThread(TicketBookingSystem system, String customerName, int seats, int priority) {
        this.system = system;
        this.customerName = customerName;
        this.seats = seats;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookTicket(customerName, seats);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        BookingThread vip1 = new BookingThread(system, "VIP Customer 1", 2, Thread.MAX_PRIORITY);
        BookingThread vip2 = new BookingThread(system, "VIP Customer 2", 3, Thread.MAX_PRIORITY);
        BookingThread regular1 = new BookingThread(system, "Regular Customer 1", 4, Thread.NORM_PRIORITY);
        BookingThread regular2 = new BookingThread(system, "Regular Customer 2", 2, Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
    }
}
