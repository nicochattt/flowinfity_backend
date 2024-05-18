package com.dahlias.flowinfity_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.data.User;
import com.dahlias.flowinfity_backend.data.UserStatut;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUid(String uid);

    List<User> findAllByStatut(UserStatut statut);

    @Query("SELECT am.association.id FROM AssociationMember am WHERE am.user.id = :userId")
    List<Association> findAllAssociationByuserId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE REPLACE(CONCAT(TRIM(u.firstname), ' ', TRIM(u.lastname)), '  ', ' ') LIKE %:name% OR REPLACE(CONCAT(TRIM(u.lastname), ' ', TRIM(u.firstname)), '  ', ' ') LIKE %:name%")
    List<User> findByFirstnameOrLastnameContaining(@Param("name") String name);
}
