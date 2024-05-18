package com.dahlias.flowinfity_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.User;
import com.dahlias.flowinfity_backend.data.UserStatut;
import com.dahlias.flowinfity_backend.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setLastname(userDetails.getLastname());
        user.setFirstname(userDetails.getFirstname());
        user.setUid(userDetails.getUid());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setRib(userDetails.getRib());
        user.setStatut(userDetails.getStatut());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public List<User> getUsersByStatut(UserStatut statut) {
        return userRepository.findAllByStatut(statut);
    }

    public Optional<UserStatut> getUserStatutByid(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getStatut);
    }

    public List<Association> getAllAssociationByuserId(Long userId) {
        return userRepository.findAllAssociationByuserId(userId);
    }

    @Cacheable(value = "user", key = "#name")
    public List<User> searchUsersByName(String name) {
        return userRepository.findByFirstnameOrLastnameContaining(name);
    }
}
