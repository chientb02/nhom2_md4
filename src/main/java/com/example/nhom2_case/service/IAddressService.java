package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Address;

import java.util.List;

public interface IAddressService extends IGenerateService<Address> {
   List<Address> findAllByIdCity(Long idCity);
}
