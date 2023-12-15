package com.example.nhom2_case.service.Impl;

import com.example.nhom2_case.model.Status;
import com.example.nhom2_case.repository.StatusRepository;
import com.example.nhom2_case.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StatusService implements IStatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findOne(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public void save(Status status) {

    }

    @Override
    public void delete(Long id) {

    }
}
