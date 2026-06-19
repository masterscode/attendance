package com.attendance.core.employee.service;

import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.data.request.EmployeeFilterRequest;
import com.attendance.core.employee.data.request.UpdateEmployeeRequest;
import com.attendance.core.employee.data.response.EmployeeResponse;
import com.attendance.core.employee.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest request);

    EmployeeResponse updateEmployee(UUID id, UpdateEmployeeRequest request);

    EmployeeResponse getEmployeeInfo(UUID departmentId);

    PagedModel<EmployeeResponse> getAllEmployees(EmployeeFilterRequest filterRequest, Pageable pageable);

    Employee getEmployeeEntity(UUID id);
}
