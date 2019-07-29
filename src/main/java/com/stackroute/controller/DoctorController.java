package com.stackroute.controller;

import com.stackroute.domain.Doctor;
import com.stackroute.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/neo4j/doctor")
public class DoctorController {

    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {

        this.doctorService = doctorService;
    }

    private Doctor doctor;

    @GetMapping("doctors")
    public ResponseEntity<?> getAll(){

        return new ResponseEntity<>(doctorService.getAll(),HttpStatus.OK);
    }


    @PostMapping("doctorsave")
    public ResponseEntity<?> saveDoctor(@RequestBody Doctor doctor) {

         return new ResponseEntity<>(doctorService.save(doctor),HttpStatus.CREATED);
    }

    @PutMapping("doctorupdate/{emailId}")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor){
        this.doctor = doctor;
        return new ResponseEntity<>(doctorService.update(doctor),HttpStatus.CREATED);
    }

    @GetMapping("doctor/{emailId}")
    public  ResponseEntity<?> getDoctor(@PathVariable String emailId){
        return new ResponseEntity<>(doctorService.getDoctorByEmailId(emailId),HttpStatus.OK);
    }


    @DeleteMapping("doctordelete/{emailId}")
    public ResponseEntity<?> delete(@PathVariable String emailId) {
        return new ResponseEntity<>(doctorService.delete(emailId),HttpStatus.OK);
    }

    @PostMapping("graph/{emailId}/{pinCode}")
    public ResponseEntity<?> createRelationBetweenDoctorDTOAndAddress(@PathVariable String emailId, @PathVariable String pinCode) {

        return new ResponseEntity<>(doctorService.save(doctor),HttpStatus.CREATED);
    }

    @GetMapping("doctorsByArea/{area}")
    ResponseEntity<?> getDoctorsByArea(@PathVariable String area){
        return new ResponseEntity<>(doctorService.getDoctorsByLocation(area),HttpStatus.OK);
    }

    @GetMapping("doctorsByAreaAndSpe/{area}/{specialization}")
    ResponseEntity<?> getDoctorsByLocationAndSpecialization(@PathVariable String area, @PathVariable String specialization){
        return new ResponseEntity<>(doctorService.getDoctorsByLocationAndSpecialization(area, specialization),HttpStatus.OK);
    }

    @GetMapping("doctorsForPatient/{emailId}")
    ResponseEntity<?>  getDoctorsByLocationAndSpecializationForPatient(@PathVariable String emailId){
        return new ResponseEntity<>(doctorService.getDoctorsByLocationAndSpecializationForPatient(emailId),HttpStatus.OK);
    }

}
