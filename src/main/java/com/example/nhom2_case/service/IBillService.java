package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Bill;


import java.time.LocalDate;
import java.util.List;

public interface IBillService extends IGenerateService<Bill>{
    void rentHome(Long id, int count_month);
    List<Bill> getBill(Long id);

}
