package com.prajwal.ticketbooking.booking;
import jakarta.persistence.*; import jakarta.validation.constraints.Min; import java.util.UUID;
@Entity @Table(name="ticket_inventory",uniqueConstraints=@UniqueConstraint(columnNames="eventId")) public class TicketInventory {
 @Id private String id=UUID.randomUUID().toString(); @Column(nullable=false,unique=true) private String eventId; @Min(0) private int availableSeats; @Version private long version;
 protected TicketInventory(){} public TicketInventory(String eventId,int availableSeats){this.eventId=eventId;this.availableSeats=availableSeats;}
 public String getId(){return id;} public String getEventId(){return eventId;} public int getAvailableSeats(){return availableSeats;} public long getVersion(){return version;}
 public void reserve(int quantity){if(quantity<1)throw new IllegalArgumentException("Quantity must be at least 1");if(availableSeats<quantity)throw new IllegalStateException("Not enough seats available");availableSeats-=quantity;}
}