package com.example.nhom2_case.service.impl;

import com.example.nhom2_case.model.Address;
import com.example.nhom2_case.repository.AddressRepository;
import com.example.nhom2_case.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddressService implements IAddressService {
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findOne(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);

    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);

    }

    @Override
    public List<Address> findAllByIdCity(Long idCity) {
        return addressRepository.findAllByIdCity(idCity);
    }
}
