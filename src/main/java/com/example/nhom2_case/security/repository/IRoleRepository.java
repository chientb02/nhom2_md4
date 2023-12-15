package com.example.nhom2_case.security.repository;


import com.example.nhom2_case.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
