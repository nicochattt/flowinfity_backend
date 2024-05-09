package com.dahlias.flowinfity_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
