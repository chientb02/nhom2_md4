package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Filter;
import com.example.nhom2_case.model.Home;
import com.example.nhom2_case.repository.FilterRepository;

import com.example.nhom2_case.service.IFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService implements IFilterService {
    @Autowired
    private FilterRepository filterRepository;

    public List<Home> searchFilter(Filter filter) {
        try {
            return filterRepository.searchFilter(
                    filter.getMinPrice(),
                    filter.getMaxPrice(),
                    filter.getStatus().getIdStatus(),
                    filter.getCity().getIdCity(),
                    filter.getAddress().getIdAddress()
            );
        } catch (Exception e) {
            e.getStackTrace();
        } return null;
    }
}
