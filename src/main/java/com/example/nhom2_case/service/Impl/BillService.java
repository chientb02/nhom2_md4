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
import java.time.temporal.ChronoUnit;
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
    public void rentHome(Long id, LocalDate checkin, LocalDate checkout, Account account) {
        LocalDate dateNow = LocalDate.now();
        if (!checkin.isBefore(dateNow) && checkout.isAfter(checkin)) {
            Optional<Home> homeOptional = homeService.findOne(id);
            if (homeOptional.isPresent()) {
                Home home = homeOptional.get();
                Bill bill = new Bill();
                bill.setHome(home);
//            account chưa có
                bill.setAccount(account);
                bill.setCheckin(checkin);
                bill.setCheckout(checkout);
                billRepository.updateStatusByIdHome(id);
                long days = ChronoUnit.DAYS.between(checkin, checkout);
                double TotalPrice = home.getPrice() * days;
                bill.setTotalPrice(TotalPrice);
                billRepository.save(bill);
            }
        }


    }

    @Override
    public List<Bill> getBill(Long id) {
        try {
            return billRepository.getBill(id);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }


}
