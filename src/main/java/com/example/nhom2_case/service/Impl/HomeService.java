package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import com.example.nhom2_case.repository.HomeRepository;
import com.example.nhom2_case.repository.ImageRepository;
import com.example.nhom2_case.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class HomeService implements IHomeService {
    @Autowired
    HomeRepository homeRepository;

    @Autowired
    ImageRepository imageRepository;
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
    public List<Image> image(Long id) {
        Optional<Home> optionalHome = findOne(id);
        if (optionalHome.isPresent()) {
            return imageRepository.findAllByHome(optionalHome.get());
        } else {
            return  new ArrayList<>();
        }
    }
}
