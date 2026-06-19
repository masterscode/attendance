package com.attendance.core.attendance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Column(name = "employee_id")
    private UUID id;
    @Column(name = "employee_name")
    private String name;
}
