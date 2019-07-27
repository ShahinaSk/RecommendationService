package com.stackroute.controller;

import com.stackroute.domain.Specialization;
import com.stackroute.service.SpecializationService;
import com.stackroute.service.SpecializationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neo4j/specialization")
public class SpecializationController {

    private SpecializationService specializationService;

    @Autowired
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping("specialization")
    public ResponseEntity<?> save(@RequestBody Specialization specialization){
        return new ResponseEntity<>(specializationService.save(specialization.getSpecialization()), HttpStatus.CREATED);
    }
}
