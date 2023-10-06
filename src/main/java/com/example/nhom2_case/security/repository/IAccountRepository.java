package com.example.nhom2_case.security.repository;


import com.example.nhom2_case.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    @Modifying
    @Query(value = "insert into account_roles (account_id, roles_id ) values (?1,?2)",nativeQuery = true)
    void addAcc(Long idAcc ,Long idRole);

    @Modifying
    @Query(value = "insert into user (account_id) values (?1)",nativeQuery = true)
    void addUser(Long idAcc );


}
