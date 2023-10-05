package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.*;
import com.example.nhom2_case.repository.StatusRepository;
import com.example.nhom2_case.service.IAddressService;
import com.example.nhom2_case.service.ICityService;
import com.example.nhom2_case.service.IFilterService;
import com.example.nhom2_case.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/filters")
public class FilterController {

    @Autowired
    IFilterService filterService;
    @Autowired
    IStatusService statusService;
    @Autowired
    ICityService cityService;
    @Autowired
    IAddressService addressService;

//    @PostMapping()
//    public ResponseEntity<List<Home>> searchHomes(@RequestParam(required = false) Double minPrice,
//                                                  @RequestParam(required = false) Double maxPrice,
//                                                  @RequestParam(required = false) Long idStatus,
//                                                  @RequestParam(required = false) Long idCity,
//                                                  @RequestParam(required = false) Long idAddress) {
//        Filter filter = new Filter();
//        filter.setMinPrice(minPrice);
//        filter.setMaxPrice(maxPrice);
//
//        Status status = statusService.findOne(idStatus).get();
//        filter.setStatus(status);
//
//        City city = cityService.findOne(idCity).get();
//        filter.setCity(city);
//
//        Address address = addressService.findOne(idAddress).get();
//        filter.setAddress(address);
//
//        return new ResponseEntity<>(filterService.searchFilter(filter), HttpStatus.OK);
//    }

    @PostMapping("/m1")
    public ResponseEntity<List<Home>> searchHomes(@RequestBody Filter filter) {
        return new ResponseEntity<>(filterService.searchFilter(filter), HttpStatus.OK);
    }



}

