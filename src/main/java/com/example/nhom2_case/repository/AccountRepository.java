package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT a.id FROM account a WHERE a.username = :username",nativeQuery = true)
    Long idAccount(@Param("username") String username);
}
