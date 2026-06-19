package com.attendance.core.attendance.entity;

import com.attendance.commons.entity.AbstractAuditingEntity;
import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.enums.AttendanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
    name = "attendance_records",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_employee_date",
        columnNames = {"employee_id", "date"}
    )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecord extends AbstractAuditingEntity {

    @Embedded
    private Subject employee;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "action")
    private AttendanceAction action;

    @Column
    private String note;

    public AttendanceType computeAttendanceStatus() {
        return switch (this.action){
            case SIGN_IN, SIGN_OUT -> AttendanceType.PRESENT;
            case SICK_LEAVE -> AttendanceType.SICK_LEAVE;
            case ABSENT -> AttendanceType.ABSENT;
            case null -> null;
        };
    }

}
