package com.stackroute.controller;

import com.stackroute.domain.Clinic;
import com.stackroute.service.ClinicService;
import com.stackroute.service.ClinicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neo4j/clinic")
public class ClinicController {

    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public ResponseEntity<?> save(@RequestBody Clinic clinic){
        return new ResponseEntity<>(clinicService.save(clinic), HttpStatus.CREATED);
    }
}
