package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User , Long> {
}
