package com.stackroute.service;

import com.stackroute.domain.*;
import com.stackroute.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {


    private DoctorRepository doctorRepository;
    private AddressServiceImpl addressService;
    private ClinicServiceImpl clinicService;
    private SpecializationServiceImpl specializationService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, AddressServiceImpl addressService,
                             ClinicServiceImpl clinicService, SpecializationServiceImpl specializationService) {
        this.doctorRepository = doctorRepository;
        this.addressService = addressService;
        this.clinicService = clinicService;
        this.specializationService = specializationService;
    }

    @Override
    public DoctorDTO save(Doctor doctor) {

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmailId(doctor.getEmailId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setProfileImage(doctor.getProfileImage());
        doctorDTO.setPracticeStartedDate(doctor.getPracticeStartedDate());
        doctorDTO = doctorRepository.save(doctorDTO);
        Clinic savedClinic = clinicService.save(new Clinic(doctor.getClinicName()));
        Address address = addressService.save(doctor.getAddress());
        Specialization specialization = specializationService.save(doctor.getSpecialization());
        doctorDTO = createRelationBetweenDoctorDTOAndAddress(doctorDTO.getEmailId(), address.getPinCode());
        doctorDTO = createRelationBetweenDoctorDTOAndClinic(doctorDTO.getEmailId(), savedClinic.getClinicName());
        doctorDTO = createRelationBetweenDoctorDTOAndSpecialization(doctorDTO.getEmailId(), specialization.getSpecialization());
        return doctorDTO;
    }

    @Override
    public String delete(String emailId) {
        doctorRepository.deleteById(emailId);
        return "Deleted Successfully";
    }

    @Override
    public DoctorDTO update(Doctor doctor) {

        Address address = addressService.save(doctor.getAddress());
        Clinic clinic = clinicService.saveClinic(doctor.getClinicName());
        DoctorDTO doctorDTO1 = doctorRepository.findById(doctor.getEmailId()).get();
        doctorDTO1 = doctorRepository.deleteRelationShipBetweenDoctorDTOAndClinic(doctorDTO1.getEmailId());
        doctorDTO1 = doctorRepository.deleteRelationShipBetweenDoctorDTOAndAddress(doctorDTO1.getEmailId());

        doctorDTO1.setName(doctor.getName());
        doctorDTO1.setProfileImage(doctor.getProfileImage());
        doctorDTO1.setPracticeStartedDate(doctor.getPracticeStartedDate());
        doctorDTO1 = doctorRepository.save(doctorDTO1);

        doctorDTO1 = doctorRepository.createRelationBetweenDoctorDTOAndClinic(doctorDTO1.getEmailId(), clinic.getClinicName());
        doctorDTO1 = doctorRepository.createRelationBetweenDoctorDTOAndAddress(doctorDTO1.getEmailId(), address.getPinCode());

        return doctorDTO1;

    }

    @Override
    public List<DoctorDTO> getAll() {
        return doctorRepository.getAll();
    }

    @Override
    public DoctorDTO getDoctorByEmailId(String emailId) {
        return doctorRepository.findById(emailId).get();
    }

    @Override
    public DoctorDTO createRelationBetweenDoctorDTOAndAddress(String emailId, String pinCode) {
        return doctorRepository.createRelationBetweenDoctorDTOAndAddress(emailId, pinCode);
    }

    @Override
    public DoctorDTO createRelationBetweenDoctorDTOAndClinic(String emailId, String clinicName) {
        return doctorRepository.createRelationBetweenDoctorDTOAndClinic(emailId, clinicName);
    }

    @Override
    public DoctorDTO createRelationBetweenDoctorDTOAndSpecialization(String emailId, String specialization) {
        return doctorRepository.createRelationBetweenDoctorDTOAndSpecialization(emailId, specialization);
    }

    @Override
    public DoctorDTO deleteRelationShipBetweenDoctorDTOAndClinic(String emailId) {
        return doctorRepository.deleteRelationShipBetweenDoctorDTOAndClinic(emailId);
    }

    @Override
    public DoctorDTO deleteRelationShipBetweenDoctorDTOAndAddress(String emailId) {
        return doctorRepository.deleteRelationShipBetweenDoctorDTOAndAddress(emailId);
    }

    @Override
    public List<DoctorDTO> getDoctorsByLocation(String area) {
        return doctorRepository.getDoctorsByLocation(area);
    }

   /* @KafkaListener(topics = "doctorcredentials",groupId = "Group_Json1",containerFactory = "kafkaListenerContainerFactory1")
    public void consumeJson1(@Payload Doctor doctor)
    {
        System.out.println("Consumed doctor"  +doctor.toString());
        Doc user=new User(doctor.getEmailId(),doctor.getPassword(),doctor.getRole());
        saveUser(user);
    }*/
}






