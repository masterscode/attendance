package com.attendance.core.attendance.repository;


import com.attendance.core.attendance.entity.AttendanceRecord;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, UUID>, JpaSpecificationExecutor<AttendanceRecord> {

    boolean existsByEmployeeIdAndDate(UUID employeeId, LocalDate date);

    Boolean existsByDateAndActionAndEmployee_Id(LocalDate date, AttendanceAction action, UUID employeeId);

    Optional<AttendanceRecord> findByEmployeeIdAndDate(UUID employeeId, LocalDate date);

    @Query("""
            SELECT a FROM AttendanceRecord a
            JOIN FETCH a.employee e
            WHERE e.id = :employeeId
              AND a.date BETWEEN :from AND :to
            ORDER BY a.date ASC
            """)
    List<AttendanceRecord> findByEmployeeIdAndDateRange(
            @Param("employeeId") UUID employeeId,
            @Param("from") LocalDate from,
            @Param("to") LocalDate to
    );

    @Query("""
            SELECT a FROM AttendanceRecord a
            WHERE a.employee.id = :employeeId
              AND a.date = :date  AND a.action = :action
            """)
    Optional<AttendanceRecord> findByEmployeeIdAndDateAndType(UUID employeeId, LocalDate date, AttendanceAction action);
}
