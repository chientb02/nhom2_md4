package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.Bill;
import com.example.nhom2_case.service.IBillService;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bills")

public class BillController {
    @Autowired
    private IBillService billService;
    @PostMapping("/{idHome}")
    public ResponseEntity<?> rentHome(@PathVariable("idHome") Long id,
                                         @Param("count_month") int count_month) {
       billService.rentHome(id, count_month);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Bill>> getBill(@Param("id") Long id) {
        List<Bill> bills = billService.getBill(id);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
