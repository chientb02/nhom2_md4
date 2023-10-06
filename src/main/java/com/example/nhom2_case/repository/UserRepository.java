package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository <User , Long> {
    User findUserByAccount_Id (Long id);
}
