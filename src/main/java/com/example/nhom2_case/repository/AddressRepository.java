package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Long> {
}
