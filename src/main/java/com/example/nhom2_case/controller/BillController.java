package com.example.nhom2_case.controller;

import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.Bill;
import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.repository.AccountRepository;
import com.example.nhom2_case.service.IBillService;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bills")

public class BillController {
    @Autowired
    private IBillService billService;
    @Autowired
    private AccountRepository accountRepository;


    @PostMapping("/{idHome}")
    public ResponseEntity<?> rentHome(@PathVariable("idHome") Long id,
                                      @RequestPart("bills") Bill bill,
                                      @RequestPart("account") String username) {
        Long idAccount = accountRepository.idAccount(username);
        Account account = accountRepository.findById(idAccount).get();
            billService.rentHome(id, bill.getCheckin(), bill.getCheckout(), account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<List<Bill>> getBill(@Param("id") Long id) {
        List<Bill> bills = billService.getBill(id);
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
