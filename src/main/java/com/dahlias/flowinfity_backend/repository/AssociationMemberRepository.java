package com.dahlias.flowinfity_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dahlias.flowinfity_backend.data.AssociationMember;
import com.dahlias.flowinfity_backend.data.AssociationMemberId;
@Repository
public interface AssociationMemberRepository extends JpaRepository<AssociationMember, AssociationMemberId> {
    
}
