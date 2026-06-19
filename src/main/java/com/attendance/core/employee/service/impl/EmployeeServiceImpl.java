package com.attendance.core.employee.service.impl;

import com.attendance.core.department.entity.Department;
import com.attendance.core.department.service.DepartmentService;
import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.data.request.EmployeeFilterRequest;
import com.attendance.core.employee.data.request.UpdateEmployeeRequest;
import com.attendance.core.employee.data.response.EmployeeResponse;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.repository.EmployeeRepository;
import com.attendance.core.employee.repository.EmployeeSpecification;
import com.attendance.core.employee.service.EmployeeFactory;
import com.attendance.core.employee.service.EmployeeService;
import com.attendance.internal.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
        Department department = departmentService.findById(request.departmentId());

        Employee employee = EmployeeFactory.create(request, department);
        final Employee saved = employeeRepository.save(employee);

        return EmployeeResponse.from(saved);
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(UUID employeeId, UpdateEmployeeRequest request) {
        final Employee employee = getEmployeeEntity(employeeId);

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setGender(request.gender());
        employee.setAddress(request.address());

        return EmployeeResponse.from(employeeRepository.save(employee));
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeInfo(UUID id) {
        return EmployeeResponse.from(this.getEmployeeEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<EmployeeResponse> getAllEmployees(EmployeeFilterRequest filterRequest, Pageable pageable) {

        Page<Employee> employees = employeeRepository.findAll(EmployeeSpecification.filter(filterRequest), pageable);

        return new PagedModel<>(employees.map(EmployeeResponse::from));
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeEntity(UUID id) throws ResourceNotFoundException {
        return employeeRepository.findByIdWithDepartment(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }
}
