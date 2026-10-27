package com.prajwal.ticketbooking.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private String id = UUID.randomUUID().toString();
    @NotBlank private String title;
    @NotBlank private String venue;
    private LocalDateTime startsAt;
    @Min(1) private int totalSeats;
    private boolean published = true;
    protected Event() {}
    public Event(String title, String venue, LocalDateTime startsAt, int totalSeats) {
        this.title = title; this.venue = venue; this.startsAt = startsAt; this.totalSeats = totalSeats;
    }
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getVenue() { return venue; }
    public LocalDateTime getStartsAt() { return startsAt; }
    public int getTotalSeats() { return totalSeats; }
    public boolean isPublished() { return published; }
}