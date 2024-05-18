package com.dahlias.flowinfity_backend.web;

import com.dahlias.flowinfity_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.User;
import com.dahlias.flowinfity_backend.data.UserStatut;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/uid/{uid}")
    public ResponseEntity<User> getUserByUid(@PathVariable String uid) {
        Optional<User> user = userService.getUserByUid(uid);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/statut")
    public Optional<UserStatut> getUserStatutByid(@PathVariable Long id) {
        return userService.getUserStatutByid(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/association")
    public List<Association> getAllAssociationByuserIdAssociations(@PathVariable Long userId) {
        return userService.getAllAssociationByuserId(userId);
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }
}
