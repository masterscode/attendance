package com.attendance.core.attendance.repository;

import com.attendance.core.attendance.entity.AttendanceRecord;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmployeeAttendanceSpecification {


    private EmployeeAttendanceSpecification() {
    }

    public static Specification<AttendanceRecord> filter(UUID employeeId, LocalDate fromDate, LocalDate toDate) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("employee").get("id"), employeeId));

            if (Objects.nonNull(fromDate)) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("date"), fromDate)
                );
            }

            if (Objects.nonNull(toDate)) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("date"), toDate)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
