package com.prajwal.ticketbooking.booking;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api") public class BookingController {
 private final BookingService service; public BookingController(BookingService service){this.service=service;}
 public record CreateBookingRequest(@NotBlank String userId,@NotBlank String eventId,@Min(1) int quantity){}
 public record CreateInventoryRequest(@NotBlank String eventId,@Min(0) int availableSeats){}
 @PostMapping("/inventory") @ResponseStatus(HttpStatus.CREATED) public TicketInventory inventory(@Valid @RequestBody CreateInventoryRequest r){return service.addInventory(r.eventId(),r.availableSeats());}
 @PostMapping("/bookings") @ResponseStatus(HttpStatus.CREATED) public Booking book(@Valid @RequestBody CreateBookingRequest r){return service.book(r.userId(),r.eventId(),r.quantity());}
 @GetMapping("/bookings/{id}") public Booking get(@PathVariable String id){return service.get(id);}
 @GetMapping("/users/{userId}/bookings") public List<Booking> byUser(@PathVariable String userId){return service.byUser(userId);}
}