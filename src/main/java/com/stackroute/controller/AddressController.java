package com.stackroute.controller;

import com.stackroute.domain.Address;
import com.stackroute.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/neo4j/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("address")
    public ResponseEntity<?> save(Address address){
        return new ResponseEntity<>(addressService.save(address), HttpStatus.CREATED);
    }
}
