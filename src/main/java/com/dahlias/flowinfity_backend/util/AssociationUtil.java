package com.dahlias.flowinfity_backend.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dahlias.flowinfity_backend.data.Association;
import com.dahlias.flowinfity_backend.repository.AssociationRepository;

@Component
public class AssociationUtil {

    private static AssociationRepository associationRepository;

    @Autowired
    public void setAssociationRepository(AssociationRepository associationRepository) {
        AssociationUtil.associationRepository = associationRepository;
    }

    public static void updateAssociationMoney(Association association, BigDecimal amount) {
        association.setBudget(association.getBudget().subtract(amount));
        associationRepository.save(association);
    }
}
