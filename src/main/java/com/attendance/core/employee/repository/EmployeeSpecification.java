package com.attendance.core.employee.repository;

import com.attendance.core.employee.data.request.EmployeeFilterRequest;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import java.util.Objects;
import java.util.UUID;

public class EmployeeSpecification {
    public static Specification<Employee> filter(EmployeeFilterRequest filter) {
        return Specification
                .where(hasFirstName(filter.firstName()))
                .and(hasLastName(filter.lastName()))
                .and(hasGender(filter.gender()))
                .and(hasEmployeeType(filter.type()))
                .and(hasDepartmentName(filter.departmentName()))
                .and(hasDepartmentId(filter.departmentId()));
    }

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, cb) ->
                StringUtils.isBlank(firstName) ? cb.conjunction() :
                        cb.like(cb.lower(root.get("firstName")),
                                "%" + firstName.toLowerCase().trim() + "%");
    }

    public static Specification<Employee> hasLastName(String lastName) {
        return (root, query, cb) ->
                StringUtils.isBlank(lastName) ? cb.conjunction() :
                        cb.like(cb.lower(root.get("lastName")),
                                "%" + lastName.toLowerCase().trim() + "%");
    }

    public static Specification<Employee> hasGender(Gender gender) {
        return (root, query, cb) ->
                Objects.isNull(gender) ? null : cb.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> hasEmployeeType(EmployeeType employeeType) {
        return (root, query, cb) ->
                Objects.isNull(employeeType) ? cb.conjunction() : cb.equal(root.get("employeeType"), employeeType);
    }

    public static Specification<Employee> hasDepartmentId(UUID departmentId) {
        return (root, query, cb) ->
                Objects.isNull(departmentId) ? cb.conjunction() :
                        cb.equal(root.get("department").get("id"), departmentId);
    }

    public static Specification<Employee> hasDepartmentName(String departmentName) {
        return (root, query, cb) ->
                StringUtils.isBlank(departmentName) ? cb.conjunction() :
                        cb.like(cb.lower(
                                        root.get("department").get("name")
                                ),
                                "%" + departmentName.toLowerCase().trim() + "%");
    }
}
