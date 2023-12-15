package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.City;
import com.example.nhom2_case.service.ICityService;
import com.example.nhom2_case.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cities")
public class CityController {
    @Autowired
    private ICityService cityService;
    @GetMapping
    public ResponseEntity<Iterable<City>> display() {
        Iterable<City> cities = cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{idCity}")
    public ResponseEntity<City> findOne(@PathVariable("idCity") Long idCity) {
        Optional<City> cityOptional = cityService.findOne(idCity);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            return new ResponseEntity<>(city,HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
