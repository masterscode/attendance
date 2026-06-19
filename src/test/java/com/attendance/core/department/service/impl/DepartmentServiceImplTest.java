package com.attendance.core.department.service.impl;

import com.attendance.core.department.data.response.DepartmentResponse;
import com.attendance.core.department.repository.DepartmentRepository;
import com.attendance.core.department.service.DepartmentService;
import com.attendance.internal.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.UUID;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private DepartmentRepository repository;


    @BeforeEach
    void clearCaches() {
        if (cacheManager != null) {
            cacheManager.getCacheNames().forEach(name -> {
                var cache = cacheManager.getCache(name);
                if (cache != null) {
                    cache.clear();
                }
            });
        }
    }


    @Test
    void getAllDepartments_shouldReturnOnlyPersistedDepartments() {
//        var cache = cacheManager.getCache("departments");
//        assert cache != null;
////        cache.clear();

        List<DepartmentResponse> response =  departmentService.getAllDepartments();
        Assertions.assertThat(response).isNotNull();

        org.junit.jupiter.api.Assertions.assertFalse(CollectionUtils.isEmpty(response));
        final String seededName = "Cardiology";
        boolean hasName = response.stream()
                .map(DepartmentResponse::name).anyMatch(name -> name.equals(seededName));
        org.junit.jupiter.api.Assertions.assertTrue(hasName);

    }


    @Test
    void findById_shouldThrowResourceNotFoundException_whenDepartmentDoesNotExist() {
        UUID unknownDepartmentId = UUID.randomUUID();

        Assertions.assertThatThrownBy(() -> departmentService.findById(unknownDepartmentId))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Department not found with id: " + unknownDepartmentId);
    }

}