package com.prajwal.ticketbooking.booking;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface TicketInventoryRepository extends JpaRepository<TicketInventory,String>{Optional<TicketInventory> findByEventId(String eventId);}