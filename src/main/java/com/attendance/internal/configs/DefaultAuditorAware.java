package com.attendance.internal.configs;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;


@Component
public class DefaultAuditorAware implements AuditorAware<UUID> {

    private static final UUID SYSTEM_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Override
    @NullMarked
    public Optional<UUID> getCurrentAuditor() {
        System.out.println("--> intended id to use" + SYSTEM_UUID);
        return Optional.of(SYSTEM_UUID);
    }
}
