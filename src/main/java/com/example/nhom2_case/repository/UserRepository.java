package com.example.nhom2_case.repository;

import com.example.nhom2_case.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository <User , Long> {
    @Query(value = "select user.id_user ,user.address ,user.age ,user.avatar\n" +
            ", user.change_role , user.full_name , user.phone , user.sex\n" +
            ",user.account_id\n" +
            "from\n" +
            " ((account inner join user on account.id = user.account_id )\n" +
            "inner join account_roles on account.id = account_roles.account_id ) where account_roles.roles_id = ?  ; " , nativeQuery = true)
    List<User> findUserByRole (Long id);
    @Query(value = "select user.id_user ,user.address ,user.age ,user.avatar\n" +
            ", user.change_role , user.full_name , user.phone , user.sex\n" +
            ",user.account_id\n" +
            "from\n" +
            " ((account inner join user on account.id = user.account_id )\n" +
            "inner join account_roles on account.id = account_roles.account_id ) where account_roles.roles_id != 1  ; " , nativeQuery = true)
    List<User> findUserByAdm ();



}
