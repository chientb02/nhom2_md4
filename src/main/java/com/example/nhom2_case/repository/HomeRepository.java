package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Bill;
import com.example.nhom2_case.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home , Long> {
    Iterable<Home> findAllByNameContaining(String search);
    @Query(value = "SELECT * FROM home h " +
            "WHERE h.account_id = :id ",nativeQuery = true)
    List<Home> getHome(@Param("id") Long id);

}
