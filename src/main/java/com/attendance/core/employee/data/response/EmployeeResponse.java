package com.attendance.core.employee.data.response;


import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class EmployeeResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private EmployeeType employeeType;
    private DepartmentResponse department;
    private Instant createdAt;
    private Instant updatedAt;

    public static EmployeeResponse from(Employee e) {
        return EmployeeResponse.builder()
                .id(e.getId())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .gender(e.getGender())
                .address(e.getAddress())
                .employeeType(e.getEmployeeType())
                .department(DepartmentResponse.from(e.getDepartment()))
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt()).build();
    }
}
