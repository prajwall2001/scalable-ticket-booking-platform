package com.prajwal.ticketbooking.booking;
import org.springframework.dao.OptimisticLockingFailureException; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import org.springframework.web.server.ResponseStatusException; import org.springframework.http.HttpStatus; import java.util.List;
@Service public class BookingService {
 private final BookingRepository bookings; private final TicketInventoryRepository inventory;
 public BookingService(BookingRepository bookings,TicketInventoryRepository inventory){this.bookings=bookings;this.inventory=inventory;}
 @Transactional public Booking book(String userId,String eventId,int quantity){
  if(quantity<1)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantity must be at least 1");
  TicketInventory stock=inventory.findByEventId(eventId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Inventory not found for event"));
  try{stock.reserve(quantity);inventory.saveAndFlush(stock);return bookings.save(new Booking(userId,eventId,quantity));}
  catch(IllegalStateException ex){throw new ResponseStatusException(HttpStatus.CONFLICT,ex.getMessage());}
  catch(OptimisticLockingFailureException ex){throw new ResponseStatusException(HttpStatus.CONFLICT,"Inventory changed concurrently; retry booking");}
 }
 @Transactional public TicketInventory addInventory(String eventId,int seats){if(seats<0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Seats cannot be negative");if(inventory.findByEventId(eventId).isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"Inventory already exists for event");return inventory.save(new TicketInventory(eventId,seats));}
 public Booking get(String id){return bookings.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found"));}
 public List<Booking> byUser(String userId){return bookings.findByUserId(userId);}
}