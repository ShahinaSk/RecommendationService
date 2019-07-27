package com.stackroute.controller;

import com.stackroute.domain.Patient;
import com.stackroute.service.PatientService;
import com.stackroute.service.PatientServiceImpl;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/neo4j/patient")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("patient")
    public ResponseEntity<?> save(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.save(patient), HttpStatus.CREATED);
    }

    @PutMapping("patientupdate")
    public ResponseEntity<?> update(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.update(patient), HttpStatus.OK);
    }

    @DeleteMapping("patient/{emailId}")
    public ResponseEntity<?> delete(@PathVariable String emailId) {
        return new ResponseEntity<>(patientService.delete(emailId), HttpStatus.OK);
    }

    @PostMapping("create/{patientMailId}/{docMailId}")
    public ResponseEntity<?> createRelationForPatientAndDoctor(@PathVariable String patientMailId, @PathVariable String docMailId) {
        return new ResponseEntity<>(patientService.createRelationForPatientAndDoctor(patientMailId, docMailId), HttpStatus.OK);
    }

    @DeleteMapping("delete/{patientMailId}/{docMailId}")
    public ResponseEntity<?> deleteRelationForPatientAndDoctor(@PathVariable String patientMailId, @PathVariable String docMailId) {
        return new ResponseEntity<>(patientService.deleteRelationForPatientAndDoctor(patientMailId, docMailId), HttpStatus.OK);
    }

}
