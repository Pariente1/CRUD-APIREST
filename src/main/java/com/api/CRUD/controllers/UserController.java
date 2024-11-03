package com.api.CRUD.controllers;

import com.api.CRUD.models.UserModel;
import com.api.CRUD.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUser(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id){
        Optional<UserModel> user = this.userService.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserModel> updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
        UserModel updatedUser = this.userService.updateById(request, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return ResponseEntity.ok("User with id " + id + " deleted");
        } else {
            return ResponseEntity.status(500).body("Error: Could not delete the user with id: " + id);
        }
    }
}
