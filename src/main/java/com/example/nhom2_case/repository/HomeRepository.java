package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home , Long> {
    @Query(value="select * from image as i where home_id_home=?", nativeQuery = true)
    List<Image> image(@Param("home_id_home") Long id);
}
