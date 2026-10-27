package com.prajwal.ticketbooking.user;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.List;
@RestController @RequestMapping("/api/users") public class UserController {
 private final UserRepository users; public UserController(UserRepository users){this.users=users;}
 public record CreateUserRequest(@NotBlank String fullName,@Email @NotBlank String email){}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public User create(@Valid @RequestBody CreateUserRequest r){if(users.findByEmail(r.email()).isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");return users.save(new User(r.fullName(),r.email()));}
 @GetMapping("/{id}") public User get(@PathVariable String id){return users.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));}
 @GetMapping public List<User> list(){return users.findAll();}
}