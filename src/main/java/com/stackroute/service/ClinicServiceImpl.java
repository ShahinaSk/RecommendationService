package com.stackroute.service;

import com.stackroute.domain.Clinic;
import com.stackroute.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicServiceImpl implements ClinicService {

    private ClinicRepository clinicRepository;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Clinic save(Clinic clinic) {

        return clinicRepository.save(clinic);
    }

    @Override
    public Clinic saveClinic(String clinicName) {
        if (clinicRepository.existsById(clinicName)){
            return clinicRepository.findById(clinicName).get();
        }
        return clinicRepository.save(new Clinic(clinicName));
    }
}
