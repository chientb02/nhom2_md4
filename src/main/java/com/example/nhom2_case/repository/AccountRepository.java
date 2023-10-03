package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , Long> {
}
