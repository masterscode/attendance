package com.attendance.core.employee.api;


import com.attendance.commons.data.ApiResponse;
import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.data.request.EmployeeFilterRequest;
import com.attendance.core.employee.data.request.UpdateEmployeeRequest;
import com.attendance.core.employee.data.response.EmployeeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/api/v1/employees")
@Tag(name = "Employees", description = "Employee management APIs")
public interface EmployeeApi {

    @PostMapping
    @Operation(summary = "Add a new employee")
    ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody CreateEmployeeRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Modify an existing employee")
    ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(@PathVariable UUID id, @RequestBody UpdateEmployeeRequest request);

    @GetMapping("/{employeeId}")
    @Operation(summary = "Retrieve employee info by ID")
    ResponseEntity<ApiResponse<EmployeeResponse>> getEmployee(@PathVariable UUID employeeId);

    @GetMapping
    @Operation(summary = "Optionally Filter / List all employees")
    ResponseEntity<ApiResponse<PagedModel<EmployeeResponse>>>
        getEmployees(EmployeeFilterRequest request, @PageableDefault Pageable pageable);
}
