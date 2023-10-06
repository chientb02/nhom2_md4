package com.example.nhom2_case.security.service.impl;


import com.example.nhom2_case.model.Account;
import com.example.nhom2_case.model.AccountPrinciple;
import com.example.nhom2_case.security.repository.IAccountRepository;
import com.example.nhom2_case.security.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements UserDetailsService, IAccountService {

    @Autowired
    private IAccountRepository iUserRepository;

    public Account findById(Long id) {
        Optional<Account> userOptional = iUserRepository.findById(id);
        return userOptional.map(this::toDTO).orElse(null);
    }

    public Account findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    public void save(Account user) {
        iUserRepository.save(user);

    }
    public void addAcc(Account account) {

        iUserRepository.addAcc(account.getId() , 2L);
    }

    public UserDetails loadUserByUsername(String username) {
        Account user = findByUsername(username);
        if (user != null) {
            return AccountPrinciple.build(user);
        }
        return null;
    }

    public Account toDTO(Account user) {
        return new Account(user.getId(), user.getUsername(), user.getRoles().toString());
    }
}
