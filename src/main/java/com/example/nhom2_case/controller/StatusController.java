package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.Status;
import com.example.nhom2_case.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("api/status")
public class StatusController {
    @Autowired
    private IStatusService statusService;
    @GetMapping
    public ResponseEntity<Iterable<Status>> display() {
        Iterable<Status> statuses = statusService.findAll();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

}
