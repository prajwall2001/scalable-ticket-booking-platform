package com.prajwal.ticketbooking.booking;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="bookings") public class Booking {
 @Id private String id=UUID.randomUUID().toString(); private String userId; private String eventId; private int quantity; @Enumerated(EnumType.STRING) private BookingStatus status=BookingStatus.CONFIRMED; private Instant createdAt=Instant.now();
 protected Booking(){} public Booking(String userId,String eventId,int quantity){this.userId=userId;this.eventId=eventId;this.quantity=quantity;}
 public String getId(){return id;} public String getUserId(){return userId;} public String getEventId(){return eventId;} public int getQuantity(){return quantity;} public BookingStatus getStatus(){return status;} public Instant getCreatedAt(){return createdAt;}
}