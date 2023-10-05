package com.example.nhom2_case.security.service.impl;



import com.example.nhom2_case.model.Role;
import com.example.nhom2_case.security.repository.IRoleRepository;
import com.example.nhom2_case.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Role findOne(Long id) {
        return iRoleRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Role> findAll() {
        return iRoleRepository.findAll();
    }
}
