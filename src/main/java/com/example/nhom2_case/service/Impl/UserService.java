package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.User;
import com.example.nhom2_case.repository.UserRepository;
import com.example.nhom2_case.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public User findByAccount(Long id) {
        return userRepository.findUserByAccount_Id(id);
    }
}
