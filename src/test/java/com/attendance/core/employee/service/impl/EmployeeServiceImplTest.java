package com.attendance.core.employee.service.impl;

import com.attendance.core.department.entity.Department;
import com.attendance.core.employee.data.request.CreateEmployeeRequest;
import com.attendance.core.employee.entity.Employee;
import com.attendance.core.employee.enums.EmployeeType;
import com.attendance.core.employee.enums.Gender;
import com.attendance.core.employee.repository.EmployeeRepository;
import com.attendance.utils.DepartmentTestDataFactory;
import com.attendance.utils.EmployeeTestDataFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.attendance.core.department.service.DepartmentService;
import com.attendance.core.employee.data.request.UpdateEmployeeRequest;
import com.attendance.core.employee.data.response.EmployeeResponse;
import com.attendance.internal.exception.ResourceNotFoundException;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private final UUID departmentId = UUID.randomUUID();

    @Test
    void createEmployee_shouldCreateEmployeeSuccessfully() {

        final UUID employeeId = UUID.randomUUID();
        final Department department = DepartmentTestDataFactory.department();
        CreateEmployeeRequest request = new CreateEmployeeRequest(
                "John",
                "Doe",
                Gender.MALE,
                "123 Main Street",
                EmployeeType.MEDICAL,
                departmentId
        );

        Employee savedEmployee = EmployeeTestDataFactory.employee();
        savedEmployee.setId(employeeId);

        Mockito.when(departmentService.findById(Mockito.any(UUID.class))).thenReturn(department);
        Mockito.when(employeeRepository.save(ArgumentMatchers.any(Employee.class)))
                .thenReturn(savedEmployee);

        EmployeeResponse response = employeeService.createEmployee(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isEqualTo(employeeId);
        Assertions.assertThat(response.getLastName()).isEqualTo("Doe");
        Assertions.assertThat(response.getGender()).isEqualTo(Gender.MALE);
        Assertions.assertThat(response.getEmployeeType()).isEqualTo(EmployeeType.MEDICAL);
        Assertions.assertThat(response.getDepartment().id()).isEqualTo(savedEmployee.getDepartment().getId());

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        org.mockito.Mockito.verify(departmentService).findById(departmentId);
        org.mockito.Mockito.verify(employeeRepository).save(employeeCaptor.capture());

        Employee capturedEmployee = employeeCaptor.getValue();
        Assertions.assertThat(capturedEmployee.getFirstName()).isEqualTo("John");
        Assertions.assertThat(capturedEmployee.getGender()).isEqualTo(Gender.MALE);
        Assertions.assertThat(capturedEmployee.getEmployeeType()).isEqualTo(EmployeeType.MEDICAL);
        Assertions.assertThat(capturedEmployee.getDepartment()).isEqualTo(department);
    }

    @Test
    void createEmployee_shouldPropagateException_whenDepartmentDoesNotExist() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(
                "Jane",
                "Smith",
                Gender.FEMALE,
                "456 Second Street",
                EmployeeType.NON_MEDICAL,
                departmentId
        );

        ResourceNotFoundException exception = new ResourceNotFoundException("Department not found with id: " + departmentId);

        Mockito.when(departmentService.findById(departmentId)).thenThrow(exception);

        Assertions.assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Department not found with id: " + departmentId);

        org.mockito.Mockito.verify(departmentService).findById(departmentId);
        org.mockito.Mockito.verify(employeeRepository, Mockito.never()).save(ArgumentMatchers.any(Employee.class));
    }

    @Test
    void updateEmployee_shouldUpdateEmployeeSuccessfully() {

        final UUID employeeId = UUID.randomUUID();

        UpdateEmployeeRequest request = new UpdateEmployeeRequest(
                "Jane",
                "Smith",
                Gender.FEMALE,
                "789 Updated Avenue"
        );

        Employee employee = EmployeeTestDataFactory.employee();

        Mockito.when(employeeRepository.findByIdWithDepartment(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(employee));

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponse response = employeeService.updateEmployee(employeeId, request);

        Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);

        ArgumentCaptor<Employee> employeeUpdateCaptor = ArgumentCaptor.forClass(Employee.class);


        Mockito.verify(employeeRepository).save(employeeUpdateCaptor.capture());

        Employee capturedEmployee = employeeUpdateCaptor.getValue();

        Assertions.assertThat(capturedEmployee.getFirstName()).isEqualTo("Jane");
        Assertions.assertThat(capturedEmployee.getLastName()).isEqualTo("Smith");
        Assertions.assertThat(capturedEmployee.getGender()).isEqualTo(Gender.FEMALE);
        Assertions.assertThat(capturedEmployee.getEmployeeType()).isEqualTo(EmployeeType.MEDICAL);
    }

    @Test
    void updateEmployee_shouldThrowResourceNotFoundException_whenEmployeeDoesNotExist() {
        final UUID employeeId = UUID.randomUUID();

        UpdateEmployeeRequest request = new UpdateEmployeeRequest(
                "Jane",
                "Smith",
                Gender.FEMALE,
                "789 Updated Avenue"
        );

        Mockito.when(employeeRepository.findByIdWithDepartment(employeeId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> employeeService.updateEmployee(employeeId, request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + employeeId);

        Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);
        Mockito.verify(employeeRepository, Mockito.never()).save(ArgumentMatchers.any(Employee.class));
    }

    @Test
    void getEmployeeInfo_shouldReturnEmployeeInfoSuccessfully() {
        final UUID employeeId = UUID.randomUUID();

        Employee employee = EmployeeTestDataFactory.employee();
        employee.setId(employeeId);

        Mockito.when(employeeRepository.findByIdWithDepartment(employeeId)).thenReturn(Optional.of(employee));

        EmployeeResponse response = employeeService.getEmployeeInfo(employeeId);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isEqualTo(employeeId);
        Assertions.assertThat(response.getFirstName()).isEqualTo("John");
        Assertions.assertThat(response.getLastName()).isEqualTo("Doe");
        Assertions.assertThat(response.getGender()).isEqualTo(Gender.MALE);
        Assertions.assertThat(response.getEmployeeType()).isEqualTo(EmployeeType.MEDICAL);
        Assertions.assertThat(response.getDepartment()).isNotNull();
        Assertions.assertThat(response.getDepartment().id()).isEqualTo(employee.getDepartment().getId());

        Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);
    }

    @Test
    void getEmployeeInfo_shouldThrowResourceNotFoundException_whenEmployeeDoesNotExist() {
        final UUID employeeId = UUID.randomUUID();

        org.mockito.Mockito.when(employeeRepository.findByIdWithDepartment(employeeId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> employeeService.getEmployeeInfo(employeeId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + employeeId);

        Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);
    }


    @Test
    void getEmployeeEntity_shouldReturnEmployeeEntitySuccessfully() {
        final UUID employeeId = UUID.randomUUID();

        Employee employee = EmployeeTestDataFactory.employee();
        employee.setId(employeeId);
        Department dept = employee.getDepartment();


        Mockito.when(employeeRepository.findByIdWithDepartment(employeeId)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeEntity(employeeId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(employee);
        Assertions.assertThat(result.getId()).isEqualTo(employeeId);
        Assertions.assertThat(result.getDepartment()).isEqualTo(dept);

        org.mockito.Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);
    }

    @Test
    void getEmployeeEntity_shouldThrowResourceNotFoundException_whenEmployeeDoesNotExist() {
        final UUID employeeId = UUID.randomUUID();

        Mockito.when(employeeRepository.findByIdWithDepartment(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> employeeService.getEmployeeEntity(employeeId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + employeeId);

        Mockito.verify(employeeRepository).findByIdWithDepartment(employeeId);
    }

}