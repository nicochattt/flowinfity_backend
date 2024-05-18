package com.dahlias.flowinfity_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.data.AssociationMemberDTO;
import com.dahlias.flowinfity_backend.data.AssociationMemberStatut;
import com.dahlias.flowinfity_backend.data.User;

@Repository
public interface AssociationMemberRepository extends JpaRepository<AssociationMember, Long> {
    @Query("SELECT am.user FROM AssociationMember am WHERE am.association.id = :associationId")
    List<User> findAllMembersByAssociationId(@Param("associationId") Long associationId);

    @Query("SELECT am.user FROM AssociationMember am WHERE am.association.id = :associationId AND am.statut = :statut")
    List<User> findAllMembersByAssociationIdAndStatut(@Param("associationId") Long associationId,
            @Param("statut") AssociationMemberStatut statut);

    boolean existsByUserIdAndAssociationId(Long userId, Long associationId);

    @Query("SELECT am.user FROM AssociationMember am WHERE am.association.id = :associationId AND am.statut IN :statuts")
    List<User> findUsersByAssociationIdAndStatuts(Long associationId, List<AssociationMemberStatut> statuts);

    @Query("SELECT new com.dahlias.flowinfity_backend.data.AssociationMemberDTO(u, am.statut) FROM AssociationMember am JOIN am.user u WHERE am.association.id = :associationId")
    List<AssociationMemberDTO> findMembersWithStatusByAssociationId(@Param("associationId") Long associationId);

}
