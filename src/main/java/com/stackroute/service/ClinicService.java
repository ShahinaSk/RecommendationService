package com.stackroute.service;

import com.stackroute.domain.Clinic;
import org.springframework.stereotype.Service;

public interface ClinicService {

    Clinic save(Clinic clinic);
    Clinic saveClinic(String clinicName);
}
