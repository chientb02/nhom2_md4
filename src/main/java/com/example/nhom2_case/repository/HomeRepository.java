package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HomeRepository extends JpaRepository<Home , Long> {
    Iterable<Home> findAllByNameContaining(String search);

}
