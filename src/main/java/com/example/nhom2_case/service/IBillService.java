package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.Bill;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface IBillService extends IGenerateService<Bill>{
    void rentHome(Long id, LocalDate checkin, LocalDate checkout, Account account);
    List<Bill> getBill(Long id);

}
