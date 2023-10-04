package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Address;
import com.example.nhom2_case.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {
    @Query(value = "SELECT *  FROM address WHERE city_id_city = :idCity", nativeQuery = true)
    List<Address> findAllByIdCity(@Param("idCity") Long idCity);
}
