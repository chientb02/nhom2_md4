package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Address;
import com.example.nhom2_case.model.City;
import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilterRepository extends JpaRepository<Home, Long> {

    @Query(value = "SELECT * FROM home h " +
            "JOIN address a ON h.address_id_address = a.id_address " +
            "JOIN city c ON a.city_id_city = c.id_city " +
            "WHERE (:minPrice IS NULL OR h.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR h.price <= :maxPrice) " +
            "AND (:count_bathroom IS NULL OR h.bathroom_count <= :count_bathroom) " +
            "AND (:count_bedroom IS NULL OR h.bedroom_count <= :count_bedroom) " +
            "AND (:idStatus IS NULL OR h.status_id_status = :idStatus) " +
            "AND (:idCity IS NULL OR a.city_id_city = :idCity) " +
            "AND (:idAddress IS NULL OR h.address_id_address = :idAddress)",
            nativeQuery = true)
    List<Home> searchFilter(@Param("minPrice") Double minPrice,
                            @Param("maxPrice") Double maxPrice,
                            @Param("count_bathroom") Integer count_bathroom,
                            @Param("count_bedroom") Integer count_bedroom,
                            @Param("idStatus") Long idStatus,
                            @Param("idCity") Long idCity,
                            @Param("idAddress") Long idAddress);
}

