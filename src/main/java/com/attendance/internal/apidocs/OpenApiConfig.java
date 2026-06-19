package com.attendance.internal.apidocs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Attendance Register API")
                        .description("REST API for managing employee attendance records in a medical organisation. " +
                                "Supports MEDICAL and NON_MEDICAL employees, departmentId filtering, " +
                                "and attendance actions: SIGN_IN, SIGN_OUT, SICK_LEAVE, ABSENT.")
                        .version("1.0.0")
                        .contact(new Contact().name("Crown Interactive")));
    }
}
