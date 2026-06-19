package com.attendance.core.department.api;


import com.attendance.commons.data.ApiResponse;
import com.attendance.core.department.data.response.DepartmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/v1/departments")
@Tag(name = "Departments", description = "Department listing APIs")
public interface DepartmentApi {

    @GetMapping
    @Operation(summary = "List all departments an employee can belong to")
    ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments();
}
