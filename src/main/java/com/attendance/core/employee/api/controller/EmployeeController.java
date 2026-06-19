package com.attendance.core.employee.api.controller;

import com.attendance.commons.data.ApiResponse;
import com.attendance.core.employee.api.EmployeeApi;
import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.data.request.EmployeeFilterRequest;
import com.attendance.core.employee.data.request.UpdateEmployeeRequest;
import com.attendance.core.employee.data.response.EmployeeResponse;
import com.attendance.core.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(CreateEmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Employee created successfully", employeeService.createEmployee(request)));
    }

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(UUID id, UpdateEmployeeRequest request) {
        return ResponseEntity.ok(
                ApiResponse.ok("Employee updated successfully", employeeService.updateEmployee(id, request)));
    }

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(UUID employeeId) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        employeeService.getEmployeeInfo(employeeId)
                )
        );
    }

    @Override
    public ResponseEntity<ApiResponse<PagedModel<EmployeeResponse>>> getEmployees(EmployeeFilterRequest request, Pageable pageable) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        employeeService.getAllEmployees(request, pageable)
                )
        );
    }

}
