package com.example.nhom2_case.service;

import com.example.nhom2_case.model.Filter;
import com.example.nhom2_case.model.Home;

import java.util.List;

public interface IFilterService {
    List<Home> searchFilter(Filter filter);
}
