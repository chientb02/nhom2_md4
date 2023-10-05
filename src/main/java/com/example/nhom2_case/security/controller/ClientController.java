package com.example.nhom2_case.security.controller;


import com.example.nhom2_case.security.DTO.sdi.ClientSdi;
import com.example.nhom2_case.security.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "create")
    public ResponseEntity<Boolean> create(
            @RequestBody ClientSdi sdi
    ) {
        return ResponseEntity.ok(clientService.create(sdi));
    }
}