package com.dahlias.flowinfity_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.AssociationStatut;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {
    List<Association> findAllByStatut(AssociationStatut statut);

    @Query(value = "SELECT * FROM associations a WHERE a.associationname REGEXP CONCAT('[[:<:]]', :name, '[[:>:]]')", nativeQuery = true)
    List<Association> findByAssociationName(@Param("name") String name);
}
