package com.example.nhom2_case.service.impl;

import com.example.nhom2_case.model.City;
import com.example.nhom2_case.repository.CityRepository;
import com.example.nhom2_case.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CityService implements ICityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findOne(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);

    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);

    }
}
