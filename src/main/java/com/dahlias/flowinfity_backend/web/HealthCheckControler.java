package com.dahlias.flowinfity_backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dahlias.flowinfity_backend.HealthCheck;
import com.dahlias.flowinfity_backend.service.HealthcheckService;

@RestController
public class HealthCheckControler {


    @Autowired
    private HealthcheckService healthcheckService;

    @GetMapping("/healthcheck")
    public HealthCheck healthCheck(){
    return healthcheckService.healthCheck();
    }
}
