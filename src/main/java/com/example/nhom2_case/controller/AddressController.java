package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.Address;
import com.example.nhom2_case.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @GetMapping
    public ResponseEntity<Iterable<Address>> display() {
        Iterable<Address> addresses = addressService.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> findOne(@PathVariable("idAddress") Long idAddress) {
        Optional<Address> addressOptional = addressService.findOne(idAddress);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            return new ResponseEntity<>(address,HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/city/{idCity}")
    public ResponseEntity<List<Address>> findAllByIdCity(@PathVariable("idCity") Long idCity) {
        List<Address> addresses = addressService.findAllByIdCity(idCity);
        if (addresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
