package com.attendance.core.department.service.impl;


import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.department.entity.Department;
import com.attendance.core.department.repository.DepartmentRepository;
import com.attendance.core.department.service.DepartmentService;
import com.attendance.internal.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    @Cacheable(value = "departments")
    public List<DepartmentResponse> getAllDepartments() {
        return  departmentRepository.findAll()
                .stream()
                .map(DepartmentResponse::from)
                .toList();
    }

    @Override
    public Department findById(UUID id) throws ResourceNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

}
