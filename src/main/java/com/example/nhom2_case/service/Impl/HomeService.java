package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.repository.HomeRepository;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HomeService implements IHomeService {
    @Autowired
    HomeRepository homeRepository;
    @Override
    public Iterable<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Optional<Home> findOne(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public void save(Home home) {
        homeRepository.save(home);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Iterable<Home> searchByName(String search) {
        return homeRepository.findAllByNameContaining(search);
    }
}
