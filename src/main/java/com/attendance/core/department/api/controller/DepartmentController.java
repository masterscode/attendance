package com.attendance.core.department.api.controller;

import com.attendance.commons.data.ApiResponse;
import com.attendance.core.department.api.DepartmentApi;
import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi {

    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartments() {
        return ResponseEntity.ok(
                ApiResponse.ok(departmentService.getAllDepartments())
        );
    }
}
