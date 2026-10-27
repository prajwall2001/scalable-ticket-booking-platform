package com.prajwal.ticketbooking.user;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
 @Id private String id=UUID.randomUUID().toString(); @NotBlank private String fullName; @Email @NotBlank private String email; private Instant createdAt=Instant.now();
 protected User() {} public User(String fullName,String email){this.fullName=fullName;this.email=email;}
 public String getId(){return id;} public String getFullName(){return fullName;} public String getEmail(){return email;} public Instant getCreatedAt(){return createdAt;}
}