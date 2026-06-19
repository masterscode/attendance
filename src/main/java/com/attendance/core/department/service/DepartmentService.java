package com.attendance.core.department.service;

import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.department.entity.Department;
import com.attendance.internal.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;


public interface DepartmentService {

    List<DepartmentResponse> getAllDepartments();

    Department findById(UUID id) throws ResourceNotFoundException;

}
