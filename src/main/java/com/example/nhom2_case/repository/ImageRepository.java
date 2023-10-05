package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image , Long> {
    List<Image> findAllByHome(Home home);
}
