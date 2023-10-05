package com.example.nhom2_case.security.service;


import com.example.nhom2_case.model.Role;

public interface IRoleService {
    Role findOne(Long id);

    Iterable<Role> findAll();
}
