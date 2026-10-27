package com.prajwal.ticketbooking.notification;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotBlank; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.Map; import java.util.UUID; import java.util.concurrent.ConcurrentHashMap;
@RestController @RequestMapping("/api/notifications") public class NotificationController {
 private final Map<String,NotificationReceipt> receipts=new ConcurrentHashMap<>();
 public record NotificationRequest(@NotBlank String recipient,@NotBlank String message,@NotBlank String bookingId){}
 public record NotificationReceipt(String id,String recipient,String message,String bookingId,Instant acceptedAt){}
 @PostMapping @ResponseStatus(HttpStatus.ACCEPTED) public NotificationReceipt send(@Valid @RequestBody NotificationRequest r){NotificationReceipt receipt=new NotificationReceipt(UUID.randomUUID().toString(),r.recipient(),r.message(),r.bookingId(),Instant.now());receipts.put(receipt.id(),receipt);return receipt;}
 @GetMapping("/{id}") public NotificationReceipt get(@PathVariable String id){NotificationReceipt r=receipts.get(id);if(r==null)throw new org.springframework.web.server.ResponseStatusException(HttpStatus.NOT_FOUND,"Notification not found");return r;}
}