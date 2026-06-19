package com.attendance.core.attendance.strategy.impl;

import com.attendance.core.attendance.enums.AttendanceAction;
import com.attendance.core.attendance.strategy.AttendanceStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AttendanceStrategyFactory {

    private final Map<AttendanceAction, AttendanceStrategy> strategies;

    public AttendanceStrategyFactory(List<AttendanceStrategy> strategyList) {
        strategies = strategyList.stream()
                .collect(Collectors.toMap(AttendanceStrategy::getSupportedAction, Function.identity()));
    }

    public AttendanceStrategy getStrategy(AttendanceAction action) {
        AttendanceStrategy strategy = strategies.get(action);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported attendance action: " + action);
        }
        return strategy;
    }
}
