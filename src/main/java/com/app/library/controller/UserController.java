package com.app.library.controller;

import com.app.library.model.dto.UserInfoDto;
import com.app.library.model.dto.UserDto;
import com.app.library.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<List<UserInfoDto>> getAllUsers() {
        List<UserInfoDto> allUsers = userService.getAllUsers();
        if (!allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserInfoDto> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<UserInfoDto> saveUser(@Valid @RequestBody UserDto user) {
        UserInfoDto savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserInfoDto> replaceUser(@PathVariable UUID id, @Valid @RequestBody UserDto user) {
        return userService.replaceUser(id, user)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted.", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(consumes = "application/json")
    ResponseEntity<String> deleteUsers(@RequestBody List<UUID> ids) {
        userService.deleteUsers(ids);
        return new ResponseEntity<>("Users have been deleted.", HttpStatus.NO_CONTENT);
    }
}
