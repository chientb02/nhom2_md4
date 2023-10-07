package com.example.nhom2_case.service;

import com.example.nhom2_case.model.User;

import java.util.List;

public interface IUserService extends IGenerateService<User>{
   List <User> findByAccount (Long id) ;
}
