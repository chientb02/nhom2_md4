package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.model.Image;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHomeService extends IGenerateService<Home>{
    List<Image> image(@Param("home_id_home") Long id);
}
