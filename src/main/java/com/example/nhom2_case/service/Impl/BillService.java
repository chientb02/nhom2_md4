package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.Bill;
import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.repository.BillRepository;

import com.example.nhom2_case.service.IBillService;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private IHomeService homeService;

    @Override
    public Iterable<Bill> findAll() {

        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findOne(Long id) {

        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);

    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);

    }

    @Override
    public void rentHome(Long id, int count_month) {
        Optional<Home> homeOptional = homeService.findOne(id);
        if (homeOptional.isPresent()) {
            Home home = homeOptional.get();
            Bill bill = new Bill();
            bill.setHome(home);
//            account chưa có
            bill.setAccount(null);
            bill.setCheckin(LocalDate.now());
            bill.setCheckout(bill.getCheckin().plusMonths(count_month));
            billRepository.updateStatusByIdHome(id);
            double TotalPrice = home.getPrice() * count_month;
            bill.setTotalPrice(TotalPrice);
            billRepository.save(bill);

        }


    }

    @Override
    public List<Bill> getBill(Long id) {
        try {
            return billRepository.getBill(id);
        } catch (Exception e) {
            e.getStackTrace();
        } return null;
    }



}
