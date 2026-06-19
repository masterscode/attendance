package com.attendance.core.department.service;


import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.department.entity.Department;
import com.attendance.core.department.repository.DepartmentRepository;
import com.attendance.internal.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Cacheable(value = "departments")
    public List<DepartmentResponse> getAllDepartments() {
        return  departmentRepository.findAll()
                .stream()
                .map(DepartmentResponse::from)
                .toList();
    }

    public Department findById(UUID id) throws ResourceNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

}
