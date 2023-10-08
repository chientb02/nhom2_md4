package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Home;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.List;

public interface IHomeService extends IGenerateService<Home>{
    Home saveWithImg(Home home);



    Iterable<Home> searchByName(String search);

    List<Home> getHome(Long id);
}
