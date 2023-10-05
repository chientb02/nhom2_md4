package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Bill;
import com.example.nhom2_case.repository.BillRepository;
import com.example.nhom2_case.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;
    @Override
    public Iterable<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Bill bill) {

    }

    @Override
    public void delete(Long id) {

    }
}
