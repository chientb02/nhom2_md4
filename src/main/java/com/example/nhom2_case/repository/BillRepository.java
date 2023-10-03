package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
