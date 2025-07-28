package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.requests.RegisterUserRequest;
import org.example.requests.UserRequest;
import org.example.mappers.UserMapper;
import org.example.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserRequest> getAllUsers(
    ){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)// same as user -> userMapper.toDto(user)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build(); //same as new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserRequest> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder) {
        var user = userMapper.toEntity(request); //makes a user entity from req data
        user.setPassword_hash( request.getPasswordHash() );
        userRepository.save(user); //JPA repo save = create/update a user with password field
        var userDto = userMapper.toDto(user); //dto shows the data that is in dto objects (name, email)
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getUserId()).toUri(); //return 201 created status
        return ResponseEntity.created(uri).body(userDto); //return status code 201 and the userDto as the body
    }
}
