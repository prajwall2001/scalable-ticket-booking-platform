package com.prajwal.ticketbooking.event;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.time.LocalDateTime; import java.util.List;
@RestController @RequestMapping("/api/events") public class EventController {
 private final EventRepository events; public EventController(EventRepository events){this.events=events;}
 public record CreateEventRequest(@NotBlank String title,@NotBlank String venue,LocalDateTime startsAt,@Min(1) int totalSeats){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Event create(@Valid @RequestBody CreateEventRequest r){return events.save(new Event(r.title(),r.venue(),r.startsAt(),r.totalSeats()));}
 @GetMapping("/{id}") public Event get(@PathVariable String id){return events.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Event not found"));}
 @GetMapping public List<Event> list(){return events.findByPublishedTrue();}
}