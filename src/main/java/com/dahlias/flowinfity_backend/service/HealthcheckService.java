package com.dahlias.flowinfity_backend.service;

import org.springframework.stereotype.Service;

import com.dahlias.flowinfity_backend.ApplicationStatus;
import com.dahlias.flowinfity_backend.HealthCheck;
@Service
public class HealthcheckService {
        public HealthCheck healthCheck(){
    return new HealthCheck(ApplicationStatus.OK, "wellcome to my tennis application!");
    }
}
