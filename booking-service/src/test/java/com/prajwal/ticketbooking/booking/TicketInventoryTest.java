package com.prajwal.ticketbooking.booking;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class TicketInventoryTest {
 @Test void reserveDecreasesAvailableSeats(){TicketInventory i=new TicketInventory("event-1",10);i.reserve(3);assertEquals(7,i.getAvailableSeats());}
 @Test void reserveRejectsWhenInsufficientSeats(){TicketInventory i=new TicketInventory("event-1",2);assertThrows(IllegalStateException.class,()->i.reserve(3));assertEquals(2,i.getAvailableSeats());}
 @Test void reserveRejectsZeroQuantity(){TicketInventory i=new TicketInventory("event-1",10);assertThrows(IllegalArgumentException.class,()->i.reserve(0));}
}