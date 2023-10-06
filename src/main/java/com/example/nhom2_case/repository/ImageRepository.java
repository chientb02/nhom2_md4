package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where image.home_id_home=?",nativeQuery = true)
    List<Image> images(Long id);
}
