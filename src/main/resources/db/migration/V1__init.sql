CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE departments
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(100)                NOT NULL UNIQUE,
    category   VARCHAR(20)                 NOT NULL,
    created_by UUID                        NOT NULL,
    updated_by UUID,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    version    INTEGER                     NOT NULL
);

CREATE TABLE employees
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name    VARCHAR(100)                NOT NULL,
    last_name     VARCHAR(100)                NOT NULL,
    gender        VARCHAR(10)                 NOT NULL,
    address       TEXT                        NOT NULL,
    employee_type VARCHAR(20)                 NOT NULL,
    department_id UUID                        NOT NULL REFERENCES departments (id),
    created_by    UUID                        NOT NULL,
    updated_by    UUID,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER                     NOT NULL
);

CREATE TABLE attendance_records
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    employee_id   UUID                        NOT NULL REFERENCES employees (id),
    date          DATE                        NOT NULL,
    action        VARCHAR(25)                 NOT NULL,
    note          VARCHAR(255),
    employee_name VARCHAR(100),

    created_by    UUID                        NOT NULL,
    updated_by    UUID,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    version       INTEGER                     NOT NULL,

    CONSTRAINT uq_employee_date UNIQUE (employee_id, date)
);


CREATE INDEX idx_employees_department ON employees (department_id);
CREATE INDEX idx_employees_type ON employees (employee_type);
CREATE INDEX idx_attendance_employee_date ON attendance_records (employee_id, date);


INSERT INTO departments (name, category, version, created_by, updated_by, created_at, updated_at)
VALUES ('Cardiology', 'MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Radiology', 'MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Surgery', 'MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Pediatrics', 'MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Neurology', 'MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Human Resources', 'NON_MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Finance', 'NON_MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Information Technology', 'NON_MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Administration', 'NON_MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Facilities', 'NON_MEDICAL', 0, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




