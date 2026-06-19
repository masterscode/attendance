package com.attendance.utils;

import com.attendance.core.department.entity.Department;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class DepartmentTestDataFactory {


        public static Department department() {
            return Department.builder()
                    .name("Cardiology")
                    .build();
        }

}
