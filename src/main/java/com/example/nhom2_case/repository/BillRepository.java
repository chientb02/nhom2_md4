package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Modifying
    @Query(value = "UPDATE home Set status_id_status = 2 WHERE id_home = :idHome", nativeQuery = true)
    void updateStatusByIdHome(@Param("idHome") Long idHome);

    @Query(value = "SELECT * FROM bill b " +
            "JOIN account a ON b.account_id = a.id " +
            "JOIN user u ON a.id = u.account_id " +
            "JOIN home h ON b.home_id_home = h.id_home " +
            "WHERE a.id = :id ",nativeQuery = true)
    List<Bill> getBill(@Param("id") Long id);

}
