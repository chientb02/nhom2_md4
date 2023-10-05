package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectWithImagesRepository extends JpaRepository<Home, Long> {
}
