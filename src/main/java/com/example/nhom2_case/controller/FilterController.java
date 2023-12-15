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
@RequestMapping("/api/filters")
public class FilterController {

    @Autowired
    IFilterService filterService;
    @Autowired
    IStatusService statusService;
    @Autowired
    ICityService cityService;
    @Autowired
    IAddressService addressService;



    @PostMapping()
    public ResponseEntity<List<Home>> searchHomes(@RequestBody Filter filter) {
        return new ResponseEntity<>(filterService.searchFilter(filter), HttpStatus.OK);
    }



}

