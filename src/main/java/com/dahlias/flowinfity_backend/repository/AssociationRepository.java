package com.dahlias.flowinfity_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.Association;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
    
}
